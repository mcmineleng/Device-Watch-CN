package org.jarsi.devicewatch.mineleng.zhcn.di

import org.jarsi.devicewatch.mineleng.zhcn.data.AppSettingsRepository
import org.jarsi.devicewatch.mineleng.zhcn.data.AppSettingsRepositoryImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.AppUsageRepository
import org.jarsi.devicewatch.mineleng.zhcn.data.AppUsageRepositoryImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.BatteryStatusReader
import org.jarsi.devicewatch.mineleng.zhcn.data.BatteryStatusReaderImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchorStore
import org.jarsi.devicewatch.mineleng.zhcn.data.ChargeAnchorStoreImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationLog
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationLogImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationStats
import org.jarsi.devicewatch.mineleng.zhcn.data.NotificationStatsImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.SystemStatsRepository
import org.jarsi.devicewatch.mineleng.zhcn.data.SystemStatsRepositoryImpl
import org.jarsi.devicewatch.mineleng.zhcn.data.UsageHistory
import org.jarsi.devicewatch.mineleng.zhcn.data.UsageHistoryImpl
import org.jarsi.devicewatch.mineleng.zhcn.widget.GlanceWidgetController
import org.jarsi.devicewatch.mineleng.zhcn.widget.WidgetController
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSystemStatsRepository(impl: SystemStatsRepositoryImpl): SystemStatsRepository

    @Binds
    @Singleton
    abstract fun bindWidgetController(impl: GlanceWidgetController): WidgetController

    @Binds
    @Singleton
    abstract fun bindAppSettingsRepository(impl: AppSettingsRepositoryImpl): AppSettingsRepository

    @Binds
    @Singleton
    abstract fun bindAppUsageRepository(impl: AppUsageRepositoryImpl): AppUsageRepository

    @Binds
    @Singleton
    abstract fun bindNotificationStats(impl: NotificationStatsImpl): NotificationStats

    @Binds
    @Singleton
    abstract fun bindUsageHistory(impl: UsageHistoryImpl): UsageHistory

    @Binds
    @Singleton
    abstract fun bindNotificationLog(impl: NotificationLogImpl): NotificationLog

    @Binds
    @Singleton
    abstract fun bindChargeAnchorStore(impl: ChargeAnchorStoreImpl): ChargeAnchorStore

    @Binds
    @Singleton
    abstract fun bindBatteryStatusReader(impl: BatteryStatusReaderImpl): BatteryStatusReader
}
