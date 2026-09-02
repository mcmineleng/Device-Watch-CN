# Device Watch CN
- **原项目**：[Device Watch](https://github.com/jrs8205)
- **原作者**：jrs8205
- **本修改版源码**：[Device Watch CN](https://github.com/mcmineleng/Device-Watch-CN)
- **修改内容** 修改app/src/main/res/values/strings.xml 为简体中文，删除对中文用户无用的app/src/main/res/values-fi

[![下载最新版本](https://img.shields.io/github/v/release/mcmineleng/Device-Watch-CN?sort=semver)](https://github.com/mcmineleng/Device-Watch-CN/releases/latest)
[![下载历史版本](https://img.shields.io/github/downloads/mcmineleng/Device-Watch-CN/total)](https://github.com/mcmineleng/Device-Watch-CN/releases)
[![许可证: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](LICENSE)

原项目标签：
[![Latest release](https://img.shields.io/github/v/release/jrs8205/Device-Watch?sort=semver)](https://github.com/jrs8205/Device-Watch/releases/latest)
[![F-Droid](https://img.shields.io/f-droid/v/org.jarsi.devicewatch.mineleng.zhcn)](https://f-droid.org/packages/org.jarsi.devicewatch.mineleng.zhcn)
[![Aptoide](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fws75.aptoide.com%2Fapi%2F7%2Fapp%2FgetMeta%3Fpackage_name%3Dorg.jarsi.devicewatch.mineleng.zhcn&query=%24.data.file.vername&label=Aptoide&prefix=v&color=FE6446)](https://device-watch.en.aptoide.com/app)
[![Downloads](https://img.shields.io/github/downloads/jrs8205/Device-Watch/total)](https://github.com/jrs8205/Device-Watch/releases)
[![Built with Jetpack Compose](https://img.shields.io/badge/Built%20with-Jetpack%20Compose-4285F4)](https://developer.android.com/jetpack/compose)
[![Latest release](https://img.shields.io/github/v/release/jrs8205/Device-Watch?sort=semver)](https://github.com/jrs8205/Device-Watch/releases/latest)
[![F-Droid](https://img.shields.io/f-droid/v/org.jarsi.devicewatch.mineleng.zhcn)](https://f-droid.org/packages/org.jarsi.devicewatch.mineleng.zhcn)
[![Aptoide](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fws75.aptoide.com%2Fapi%2F7%2Fapp%2FgetMeta%3Fpackage_name%3Dorg.jarsi.devicewatch.mineleng.zhcn&query=%24.data.file.vername&label=Aptoide&prefix=v&color=FE6446)](https://device-watch.en.aptoide.com/app)
[![Downloads](https://img.shields.io/github/downloads/jrs8205/Device-Watch/total)](https://github.com/jrs8205/Device-Watch/releases)
[![Built with Jetpack Compose](https://img.shields.io/badge/Built%20with-Jetpack%20Compose-4285F4)](https://developer.android.com/jetpack/compose)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](LICENSE)

Device Watch CN 是一款 Android 设备监控应用，配备 Jetpack Glance 桌面小部件、按应用维度的使用情况洞察（屏幕时间、数据流量、通知等），以及适用于充电或底座模式的交互式屏保。

应用语言为中文

## 截图

<p>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/01.png" width="24%" alt="桌面小部件" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/02.png" width="24%" alt="概览标签页" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/03.png" width="24%" alt="屏幕使用时长" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/04.png" width="24%" alt="本次充电视图" />
</p>
<p>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/05.png" width="24%" alt="使用历史" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/06.png" width="24%" alt="最常打开和流量排行" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/07.png" width="24%" alt="最后打开列表" />
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/08.png" width="24%" alt="充电屏保" />
</p>

## 功能特性

- 桌面小部件，显示电池、内存、CPU、存储、Wi-Fi、移动网络、数据用量、运行时间、今日屏幕时间和上次更新时间
- 点击小部件任意位置即可打开应用
- 数据计数器支持按自然日或按月账单周期（可配置起始日，自动处理各月天数差异）；该设置同时应用于小部件和应用内的数据行
- 标签式仪表盘界面，支持标签间滑动切换：首页（实时电池环、使用计数器、数据计数器和 内存/CPU/存储仪表——为不使用小部件的用户提供完整功能对等）、应用（使用情况）、设备（硬件、SIM 和 WLAN 详情）和设置
- 首次运行引导，介绍小部件、屏保、计数器和历史功能，并引导权限设置，说明每项权限的作用；可随时从设置中重播
- 每个页面均支持下拉刷新，按钮带有触感反馈
- 应用标签页：类似数字健康风格的屏幕时间甜甜圈图（排行前列的应用 + 其他），图例可点击；今日流量消耗排行；以及最后打开列表（最早打开和从未使用的应用优先，可反转排序），支持按应用卸载；主屏幕启动器不参与使用排行
- 按应用详情面板：屏幕时间、打开次数、最后打开时间、今日数据用量和通知数
- 首页标签页上的使用计数器，与数据计数器共享相同的时间范围（日/账单周期）：总屏幕时间、屏幕解锁次数（API 28+）、过滤后的通知计数（不统计持续通知、分组摘要和已存在通知的更新，因此数字真实可信）、设备重启次数和充电会话次数
- 应用自行保留 62 天的每日历史记录（Android 没有追溯 API）：解锁和屏幕时间从 Android 保留的约 7 天数据回填，重启次数从 BOOT_COUNT 增量推导（不受 Android 在应用更新后重新发送 BOOT_COMPLETED 的影响），通知和充电计数从安装时开始累积
- 设备端通知日志，包含应用名称、时间戳、标题和内容；保留 7 天；点击条目可打开发送该通知的应用（如果仍然安装）
- 历史页面（从使用情况卡片进入）显示保留的 62 天每日详细数值——屏幕时间、解锁次数、通知数、设备重启次数和充电会话次数；每个指标都标明从何时开始收集，页面打开时会自动刷新
- 历史页面上的月度数据用量历史：按自然月统计的计费移动数据和 WLAN 总量，直接来自 Android 自身的统计数据，最长可回溯 12 个月（无需本地存储）
- 自充电以来页面（从电池卡片进入）：自上次电池充满电以来的时间段——或者当充电未满时，自充电器拔下以来的时间段——包含经过时间、电池耗电量和平均耗电率、解锁次数、通知数、Wi-Fi/移动数据用量，以及该时间段内按应用统计的屏幕时间甜甜圈图（Android 不向第三方应用暴露真实的按应用电池百分比，因此该页面显示诚实的使用量数据）
- 今日屏幕时间也会显示在小部件底部，刷新频率不超过每分钟一次，以免影响 5 秒的小部件更新循环
- 应用标签页上的今日最常打开列表，最后打开行显示今天使用过的应用的时钟时间（遵循系统 12/24 小时制），并带有两档陈旧颜色提示：1 个月未用显示琥珀色，3 个月未用或从未使用显示红色（对应 Google 的应用休眠阈值）
- 特殊访问权限按钮显示绿色/红色状态点，表示已授予/缺失权限
- 在双 SIM 卡设备上，在移动网络名称旁显示当前数据 SIM 卡运营商
- 隐私仪表盘快捷方式，查看按应用划分的位置/麦克风/摄像头使用情况（系统视图；这些数据不向第三方应用暴露）
- 交互式 Android 屏保，包含大时钟、日期、下一个闹钟、充电状态、电池百分比、电压、温度和实时充电功率（瓦特）
- 屏保时钟遵循设备 12/24 小时制设置，秒针同步
- 屏保中带有基于电池电量的渐变色背景和柔和脉动的充电指示器
- 可选的屏保调暗模式：手动，或在可配置的夜间时段自动开启（默认 22:00–07:00）
- 记住屏保旋转设置，用于重复充电会话；背景渐变会随 180° 布局切换而镜像翻转
- 更大的屏保旋转触控区域，便于操作
- 屏保激活时的电池充满通知
- 中文默认语言
- 位置、电话状态、附近 Wi-Fi 设备和通知的运行时权限处理
- 使用情况访问快捷方式，用于网络和应用使用统计
- 发布版本配置了 R8 代码混淆和资源压缩

每个指标都是真实的 Android 和内核源数据。当权限不足导致数值不可用时，界面会显示破折号（`—`）而非伪造的值。

## 下载

Device Watch CN 可通过 [Release](https://github.com/mcmineleng/Device-Watch-CN/releases/latest) 获取

需要 **Android 8.0（API 26）** 或更高版本。

## 问题排查

常见问题——屏保不启动、移动数据流量显示为零、通知计数、历史保留等——均在 [原版问题排查页面](https://github.com/jrs8205/Device-Watch/wiki/Troubleshooting) 中解答。

## 架构

应用遵循 MVVM + 仓储结构，使用 Hilt 依赖注入。

```
presentation/   DashboardViewModel, AppsViewModel, HistoryViewModel 和
                SinceChargeViewModel（StateFlow UI 状态）
presentation/ui 纯 Compose 屏幕代码：SystemDashboardScreen 脚手架，包含
                Material 3 NavigationBar，首页/应用/设备/设置标签页和
                共享组件（SettingsSectionCard, DeviceInfoRow, AppIcon,
                ScreenTimeDonut, AppDetailSheet）
data/           SystemStatsRepository + AppUsageRepository（按应用使用情况，按需加载）
                AppSettingsRepository（数据计数器模式、周期起始日、排序方式）
                NotificationStats + UsageHistory（自有每日计数，62天保留）
                SystemStatsParser, DataPeriodCalculator, UsageEventAggregator,
                NotificationCounting（纯函数、单元测试覆盖的计算）
                SystemStats / AppUsage 数据模型（包含 UNAVAILABLE_* 哨兵值）
widget/         Glance DashboardWidget, WidgetStateUpdater（DataStore 写入），
                WidgetController（ViewModel 交互的端口），receiver 和 actions
system/         SystemMonitorService（前台服务）, MonitorDreamService（屏保）,
                NotificationCounterService（通知监听器）
di/             Hilt 模块和入口点
```

- `SystemStatsRepositoryImpl` 是单一数据源。它是一个 `@Singleton`，在注入的调度器上从主线程外读取系统/内核源，并使用 `Mutex` 序列化其 CPU 负载快照。
- `SystemDashboardScreen` 使用 `collectAsStateWithLifecycle()` 观察 ViewModel，并通过 `hiltViewModel()` 获取。四个标签页是 `HorizontalPager` 的页面，其状态是当前选中标签页的唯一数据源——滑动和底部导航栏共同驱动它（不使用导航库）；每个页面保持自己的滚动位置。权限请求和前台服务启动保持在屏幕级别。
- 服务和 widget receiver 使用 `@AndroidEntryPoint`；Glance `ActionCallback` 通过 Hilt `EntryPoint` 访问依赖图。

## 技术栈

- Kotlin 2.4, AGP 9.2.1, Gradle 9.6
- `compileSdk 36`, `minSdk 26`, `targetSdk 35`
- Jetpack Compose（BOM 2026.06.00）+ Material 3, Jetpack Glance 1.1.1
- Hilt 2.60 配合 KSP 2.3.9
- AndroidX Lifecycle 2.10, Activity 1.13, DataStore 1.2, WorkManager 2.11
- 依赖通过 `gradle/libs.versions.toml` 版本目录管理

## 语言行为

Android 根据资源限定符选择 UI 语言：

- 汉语和非汉语用户均使用汉语的 `app/src/main/res/values/strings.xml`

## 权限

应用仅请求当前功能集使用的权限：

- `RECEIVE_BOOT_COMPLETED`
- `FOREGROUND_SERVICE`
- `FOREGROUND_SERVICE_SPECIAL_USE`
- `POST_NOTIFICATIONS`
- `ACCESS_NETWORK_STATE`
- `ACCESS_WIFI_STATE`
- `ACCESS_COARSE_LOCATION`
- `ACCESS_FINE_LOCATION`
- `NEARBY_WIFI_DEVICES`
- `READ_PHONE_STATE`
- `PACKAGE_USAGE_STATS`
- `QUERY_ALL_PACKAGES`（解析按应用数据列表的名称/图标；应用在 Google Play 外分发）
- `REQUEST_DELETE_PACKAGES`（通过系统对话框从最后打开列表卸载）

通知计数额外使用可选的“通知访问”特殊权限（`NotificationListenerService`）；授予访问权限后开始计数。不请求勿扰模式和蓝牙控制权限。

## 构建

从仓库根目录使用 Gradle 包装器：

```powershell
.\gradlew.bat :app:compileDebugKotlin
.\gradlew.bat :app:testDebugUnitTest
.\gradlew.bat :app:lintDebug
.\gradlew.bat :app:assembleDebug
.\gradlew.bat :app:assembleRelease
```

发布版本使用 R8 代码混淆和资源压缩。除非存在本地 `keystore.properties` 和密钥库，否则发布 APK 是未签名的。

## 测试

JVM 单元测试覆盖纯解析/数学逻辑和 ViewModel：

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

- `SystemStatsParserTest` — CPU 负载增量、频率驻留/压力、电池损耗、移动网络代际映射、Wi-Fi SSID/频段、信号过滤
- `DataPeriodCalculatorTest` — 账单周期数学（起始日跨月夹紧、闰年二月、跨年回绕）
- `UsageEventAggregatorTest` — 前台会话折叠（应用内 Activity 切换、未关闭会话）、甜甜圈分段、最后使用排序、日期计算、陈旧度分层和启动计数排名
- `NotificationCountingTest` — “真实通知”过滤器和计数保留/清理
- `UsageHistoryLogicTest` — BOOT_COUNT 增量去重和历史键保留
- `WidgetFormattingTest` — 小部件显示格式化（固定区域设置）、自适应 MB/GB 数据量
- `ChargeAnchorLogicTest` — 自充电以来锚点状态机（满充锚点 vs 拔充锚点、重启持久化）
- `NotificationLogCodecTest` / `NotificationLogImplTest` — 通知日志行转义、保留和排序
- `HistoryListLogicTest` — 按指标的历史修剪和“自…起收集”标签
- `SinceChargeNoticesTest` — 使用情况访问和过时期限通知的可见性
- `DashboardViewModelTest` — 刷新、透明度加载/提交、数据计数器设置、每日计数器、小部件已安装标志（手写 fakes）
- `AppsViewModelTest` — 应用标签页加载、无使用权限时的空状态、详情组装、排序切换持久化
- `HistoryViewModelTest` — 历史页面加载和静默刷新
- `SinceChargeViewModelTest` — 自充电以来窗口查询、空状态、非重叠刷新
- `ClockFitTest` — 屏保时钟宽度适配计算
- `DreamLogicTest` — 夜间调暗窗口（含跨午夜）和充电功率归一化

## 构建输出

```text
app/build/outputs/apk/debug/app-debug.apk
app/build/outputs/apk/release/app-release.apk
```

APK 和签名文件已通过 Git 忽略。

## 项目结构

```text
app/src/main/java/org/jarsi/devicewatch/mineleng/zhcn
  MainActivity.kt
  MonitorApp.kt
  data/
    AppSettingsRepository.kt
    AppSettingsRepositoryImpl.kt
    AppUsage.kt
    AppUsageRepository.kt
    AppUsageRepositoryImpl.kt
    BatteryStatusReader.kt
    ChargeAnchor.kt
    ChargeAnchorStoreImpl.kt
    DataPeriod.kt
    NotificationCounting.kt
    NotificationLog.kt
    NotificationLogImpl.kt
    NotificationStats.kt
    NotificationStatsImpl.kt
    SystemStats.kt
    SystemStatsParser.kt
    SystemStatsRepository.kt
    SystemStatsRepositoryImpl.kt
    UsageEventAggregator.kt
    UsageHistory.kt
    UsageHistoryImpl.kt
  di/
    DispatchersModule.kt
    RepositoryModule.kt
    RepositoryEntryPoint.kt
  presentation/
    AppsViewModel.kt
    DashboardViewModel.kt
    HistoryViewModel.kt
    SinceChargeViewModel.kt
    ui/
      AppDetailSheet.kt
      AppsTab.kt
      DashboardComponents.kt
      DashboardTabs.kt
      DeviceTab.kt
      HistoryListLogic.kt
      HistoryPage.kt
      OverviewTab.kt
      ScreenTimeDonut.kt
      SettingsTab.kt
      SinceChargeNotices.kt
      SinceChargePage.kt
  system/
    BatteryFullNotifier.kt
    DreamPreferences.kt
    MonitorDreamService.kt
    NotificationCounterService.kt
    SystemMonitorService.kt
  widget/
    DashboardWidget.kt
    DashboardWidgetReceiver.kt
    RefreshStatsAction.kt
    WidgetController.kt
    WidgetStateUpdater.kt

app/src/test/java/org/jarsi/devicewatch/
  data/ChargeAnchorLogicTest.kt
  data/DataPeriodCalculatorTest.kt
  data/NotificationCountingTest.kt
  data/NotificationLogCodecTest.kt
  data/NotificationLogImplTest.kt
  data/SystemStatsParserTest.kt
  data/UsageEventAggregatorTest.kt
  data/UsageHistoryLogicTest.kt
  presentation/AppsViewModelTest.kt
  presentation/DashboardViewModelTest.kt
  presentation/Fakes.kt
  presentation/HistoryViewModelTest.kt
  presentation/SinceChargeViewModelTest.kt
  presentation/ui/HistoryListLogicTest.kt
  presentation/ui/SinceChargeNoticesTest.kt
  system/ClockFitTest.kt
  system/DreamLogicTest.kt
  widget/WidgetFormattingTest.kt
```

## 许可证

Device Watch CN 是自由软件，遵循 GNU 通用公共许可证第 3 版或（由您选择）任何更高版本（SPDX: `GPL-3.0-or-later`）。完整文本请参阅 [LICENSE](LICENSE) 文件。
