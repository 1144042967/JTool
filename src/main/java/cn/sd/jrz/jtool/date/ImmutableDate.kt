package cn.sd.jrz.jtool.date

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * @author 江荣展
 * @date 2020/6/30
 */
class ImmutableDate(private val date: Instant) {

    companion object {
        fun of(date: String, format: String = "yyyy-MM-dd HH:mm:ss"): ImmutableDate? {
            return try {
                ImmutableDate(SimpleDateFormat(format).parse(date))
            } catch (e: Exception) {
                null
            }
        }

        fun of(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): ImmutableDate? {
            return ImmutableDate(LocalDateTime.of(year, month, day, hour, minute, second))
        }
    }

    constructor() : this(Date().toInstant())

    constructor(date: Date) : this(date.toInstant())

    constructor(calendar: Calendar) : this(calendar.time.toInstant())

    constructor(localDateTime: LocalDateTime) : this(localDateTime.atZone(ZoneId.systemDefault()).toInstant())

    fun toDate(): Date = Date.from(date)

    fun toCalendar(): Calendar = Calendar.getInstance().apply { time = toDate() }

    fun toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(date, ZoneId.systemDefault())

    fun addSeconds(seconds: Long) = ImmutableDate(toLocalDateTime().plusSeconds(seconds))

    fun addMinutes(minutes: Long) = ImmutableDate(toLocalDateTime().plusMinutes(minutes))

    fun addHours(hours: Long) = ImmutableDate(toLocalDateTime().plusHours(hours))

    fun addDays(days: Long) = ImmutableDate(toLocalDateTime().plusDays(days))

    fun addMonths(months: Long) = ImmutableDate(toLocalDateTime().plusMonths(months))

    fun addYears(years: Long) = ImmutableDate(toLocalDateTime().plusYears(years))

    val second get() = toLocalDateTime().second

    val minute get() = toLocalDateTime().minute

    val hour get() = toLocalDateTime().hour

    val day get() = toLocalDateTime().dayOfMonth

    val month get() = toLocalDateTime().monthValue

    val year get() = toLocalDateTime().year

    fun isBefore(immutableDate: ImmutableDate) = toLocalDateTime().isBefore(immutableDate.toLocalDateTime())

    fun isBefore(date: Date) = isBefore(ImmutableDate(date))

    fun isBefore(localDateTime: LocalDateTime) = isBefore(ImmutableDate(localDateTime))

    fun isBefore(calendar: Calendar) = isBefore(ImmutableDate(calendar))

    fun isAfter(immutableDate: ImmutableDate) = toLocalDateTime().isAfter(immutableDate.toLocalDateTime())

    fun isAfter(date: Date) = isAfter(ImmutableDate(date))

    fun isAfter(localDateTime: LocalDateTime) = isAfter(ImmutableDate(localDateTime))

    fun isAfter(calendar: Calendar) = isAfter(ImmutableDate(calendar))

    operator fun compareTo(immutableDate: ImmutableDate): Int {
        return when {
            isBefore(immutableDate) -> -1
            isAfter(immutableDate) -> 1
            else -> 0
        }
    }

    operator fun compareTo(date: Date): Int {
        return when {
            isBefore(date) -> -1
            isAfter(date) -> 1
            else -> 0
        }
    }

    operator fun compareTo(localDateTime: LocalDateTime): Int {
        return when {
            isBefore(localDateTime) -> -1
            isAfter(localDateTime) -> 1
            else -> 0
        }
    }

    operator fun compareTo(calendar: Calendar): Int {
        return when {
            isBefore(calendar) -> -1
            isAfter(calendar) -> 1
            else -> 0
        }
    }

    val millis get() = toDate().time

    override fun toString(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(toDate())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ImmutableDate
        if (date != other.date) return false
        return true
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }
}