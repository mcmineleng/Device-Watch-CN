package org.jarsi.devicewatch.mineleng.zhcn.presentation

import org.jarsi.devicewatch.mineleng.zhcn.data.AppDataUsage
import org.jarsi.devicewatch.mineleng.zhcn.data.BatteryStatusReader
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchor
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchorLogic
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchorStore
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchorType
import org.jarsi.devicewatch.mineleng.zhcn.data.DataUsageSince
import org.jarsi.devicewatch.mineleng.zhcn.data.DeviceInfo
import org.jarsi.devicewatch.mineleng.zhcn.data.MonthlyDataUsage
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationLog
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationLogEntry
import org.jarsi.devicewatch.mineleng.zhcn.data.SystemStats
import org.jarsi.devicewatch.mineleng.zhcn.data.SystemStatsRepository
import org.jarsi.devicewatch.mineleng.zhcn.data.AppScreenTime
import org.jarsi.devicewatch.mineleng.zhcn.data.UNAVAILABLE_INT
import org.jarsi.devicewatch.mineleng.zhcn.data.AppUsageRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SinceChargeViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before fun setUp() = Dispatchers.setMain(dispatcher)
    @After fun tearDown() = Dispatchers.resetMain()

    private class FakeAnchorStore(var state: ChargeAnchorLogic.State) : ChargeAnchorStore {
        var loadCalls = 0
            private set
        override fun load(): ChargeAnchorLogic.State {
            loadCalls++
            return state
        }
        override fun save(state: ChargeAnchorLogic.State) {
            this.state = state
        }
    }

    private class FakeBattery(var level: Int? = 50, var charging: Boolean = false) : BatteryStatusReader {
        override fun currentLevel(): Int? = level
        override fun isCharging(): Boolean = charging
    }

    private class FakeStatsRepository(
        var wifiGb: Double = 0.5,
        var mobileGb: Double = 0.1,
    ) : SystemStatsRepository {
        override suspend fun getStats(): SystemStats = error("not used by SinceChargeViewModel")
        override suspend fun getDeviceInfo(): DeviceInfo = error("not used by SinceChargeViewModel")
        override suspend fun dataUsedSince(startMillis: Long) = DataUsageSince(wifiGb, mobileGb)
        override suspend fun monthlyDataUsage(monthsBack: Int): List<MonthlyDataUsage> = emptyList()
    }

    private class FakeLog(private val entries: List<NotificationLogEntry>) : NotificationLog {
        override fun append(entry: NotificationLogEntry) = Unit
        override fun entriesNewestFirst(): List<NotificationLogEntry> =
            entries.sortedByDescending { it.timeMillis }
    }

    private fun screenTime(pkg: String, millis: Long) =
        AppScreenTime(pkg, "label-$pkg", millis, launchCount = 1, lastUsedMillis = 0L)

    private fun anchorState(timeMillis: Long = 1_000L) = ChargeAnchorLogic.State(
        anchor = ChargeAnchor(timeMillis, 100, ChargeAnchorType.FULL_CHARGE),
        fullReachedThisPlug = false,
    )

    private fun logEntry(timeMillis: Long) =
        NotificationLogEntry(timeMillis, "p", "App", "Title", "Text")

    private fun buildViewModel(
        store: ChargeAnchorStore = FakeAnchorStore(anchorState()),
        battery: BatteryStatusReader = FakeBattery(),
        appUsage: AppUsageRepository = FakeAppUsageRepository(),
        stats: SystemStatsRepository = FakeStatsRepository(),
        notifications: FakeNotificationStats = FakeNotificationStats(enabled = true),
        log: NotificationLog = FakeLog(emptyList()),
    ) = SinceChargeViewModel(store, battery, appUsage, stats, notifications, log, dispatcher)

    /** Suspends every screen-time query on [gate] and counts the entries. */
    private class GatedAppUsageRepository(
        private val gate: CompletableDeferred<Unit>,
    ) : AppUsageRepository by FakeAppUsageRepository() {
        var screenTimeQueries = 0

        override suspend fun screenTimeSince(startMillis: Long): List<AppScreenTime> {
            screenTimeQueries++
            gate.await()
            return emptyList()
        }
    }

    @Test
    fun `a pull during an in-flight load runs a fresh reload right after it`() = runTest {
        val store = FakeAnchorStore(anchorState())
        val viewModel = buildViewModel(store = store)

        viewModel.load()
        viewModel.refresh()
        dispatcher.scheduler.advanceUntilIdle()

        // The pull chains a second, fresh load instead of being satisfied by the
        // in-flight one (which may carry stale anchor/battery state).
        assertThat(store.loadCalls).isEqualTo(2)
        assertThat(viewModel.uiState.value.isRefreshing).isFalse()
    }

    @Test
    fun `without an anchor only the battery snapshot is exposed`() = runTest {
        val viewModel = buildViewModel(
            store = FakeAnchorStore(ChargeAnchorLogic.State()),
            battery = FakeBattery(level = 42, charging = true),
        )

        viewModel.load()
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.anchor).isNull()
        assertThat(state.currentLevel).isEqualTo(42)
        assertThat(state.isCharging).isTrue()
        assertThat(state.screenTimes).isEmpty()
    }

    @Test
    fun `with an anchor usage, data and notification counts cover the window`() = runTest {
        val appUsage = FakeAppUsageRepository(
            screenTimes = listOf(
                screenTime("com.launcher", 9_000),
                screenTime("com.whatsapp", 3_000),
            ),
            launchers = setOf("com.launcher"),
            unlocksSince = 5,
        )
        val viewModel = buildViewModel(
            store = FakeAnchorStore(anchorState(timeMillis = 1_000L)),
            battery = FakeBattery(level = 63, charging = false),
            appUsage = appUsage,
            stats = FakeStatsRepository(wifiGb = 1.5, mobileGb = 0.25),
            log = FakeLog(listOf(logEntry(500L), logEntry(1_500L), logEntry(2_000L))),
        )

        viewModel.load()
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.anchor?.timeMillis).isEqualTo(1_000L)
        assertThat(state.currentLevel).isEqualTo(63)
        assertThat(state.isCharging).isFalse()
        assertThat(state.unlockCount).isEqualTo(5)
        assertThat(state.notificationCount).isEqualTo(2)
        assertThat(state.wifiGb).isEqualTo(1.5)
        assertThat(state.mobileGb).isEqualTo(0.25)
        assertThat(state.screenTimes.map { it.packageName }).containsExactly("com.whatsapp")
        assertThat(state.screenTimeSegments.map { it.packageName }).containsExactly("com.whatsapp")
        assertThat(state.totalScreenTimeMillis).isEqualTo(3_000L)
    }

    @Test
    fun `listener disabled leaves the notification count unavailable`() = runTest {
        val viewModel = buildViewModel(
            notifications = FakeNotificationStats(enabled = false),
            log = FakeLog(listOf(logEntry(1_500L))),
        )

        viewModel.load()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value.notificationCount).isEqualTo(UNAVAILABLE_INT)
    }

    @Test
    fun `a refresh while a load is in flight does not start a second load`() = runTest {
        val gate = CompletableDeferred<Unit>()
        val slowUsage = GatedAppUsageRepository(gate)
        val viewModel = buildViewModel(appUsage = slowUsage)

        viewModel.load()
        dispatcher.scheduler.runCurrent()
        viewModel.load()
        dispatcher.scheduler.runCurrent()
        gate.complete(Unit)
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(slowUsage.screenTimeQueries).isEqualTo(1)
    }

    @Test
    fun `an anchor change during a slow load is picked up by the next refresh`() = runTest {
        val gate = CompletableDeferred<Unit>()
        val store = FakeAnchorStore(anchorState(timeMillis = 1_000L))
        val viewModel = buildViewModel(store = store, appUsage = GatedAppUsageRepository(gate))

        viewModel.load()
        dispatcher.scheduler.runCurrent()
        store.state = anchorState(timeMillis = 2_000L)
        viewModel.load()
        gate.complete(Unit)
        dispatcher.scheduler.advanceUntilIdle()

        // The in-flight load's result lands; the newer anchor was not queried yet.
        assertThat(viewModel.uiState.value.anchor?.timeMillis).isEqualTo(1_000L)

        viewModel.load()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value.anchor?.timeMillis).isEqualTo(2_000L)
    }

    @Test
    fun `missing unlock support leaves the unlock count unavailable`() = runTest {
        val viewModel = buildViewModel(
            appUsage = FakeAppUsageRepository(supportsUnlocks = false, unlocksSince = null),
        )

        viewModel.load()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(viewModel.uiState.value.unlockCount).isEqualTo(UNAVAILABLE_INT)
    }
}
