package org.jarsi.devicewatch.data

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId

/** How the data-usage counters accumulate: per calendar day or per monthly billing cycle. */
enum class DataCounterMode { DAY, BILLING_CYCLE }

/** One calendar month as an epoch-milli window; [endMillis] is exclusive (start of next month). */
data class MonthlyUsageRange(val month: YearMonth, val startMillis: Long, val endMillis: Long)

/** Pure calendar math for the data-counter period. No clock, no zone — callers pass `today`. */
object DataPeriodCalculator {

    /**
     * First day of the current counting period.
     *
     * In [DataCounterMode.BILLING_CYCLE] the period is one month long and starts on
     * [cycleStartDay], clamped to each month's length via [YearMonth.lengthOfMonth]
     * — so a start day of 31 becomes Feb 28/29 in February and Apr 30 in April.
     * If this month's (clamped) start day is still in the future, the period began
     * in the previous month.
     */
    fun periodStart(mode: DataCounterMode, cycleStartDay: Int, today: LocalDate): LocalDate {
        if (mode == DataCounterMode.DAY) return today

        val day = cycleStartDay.coerceIn(1, 31)
        val thisMonth = YearMonth.from(today)
        val candidate = thisMonth.atDay(day.coerceAtMost(thisMonth.lengthOfMonth()))
        if (!candidate.isAfter(today)) return candidate

        val previousMonth = thisMonth.minusMonths(1)
        return previousMonth.atDay(day.coerceAtMost(previousMonth.lengthOfMonth()))
    }

    /**
     * The current month plus [monthsBack] previous calendar months, newest first.
     * The current month's window intentionally extends to the start of the next month;
     * usage queries clamp to "now" by themselves.
     */
    fun monthRanges(today: LocalDate, zone: ZoneId, monthsBack: Int = 12): List<MonthlyUsageRange> {
        val current = YearMonth.from(today)
        return (0..monthsBack).map { offset ->
            val month = current.minusMonths(offset.toLong())
            MonthlyUsageRange(
                month = month,
                startMillis = month.atDay(1).atStartOfDay(zone).toInstant().toEpochMilli(),
                endMillis = month.plusMonths(1).atDay(1).atStartOfDay(zone).toInstant().toEpochMilli(),
            )
        }
    }
}
