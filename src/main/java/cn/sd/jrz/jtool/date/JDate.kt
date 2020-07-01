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
class JDate(private val date: Instant) {

    companion object {
        fun of(date: String, format: String = "yyyy-MM-dd HH:mm:ss"): JDate? {
            return try {
                JDate(SimpleDateFormat(format).parse(date))
            } catch (e: Exception) {
                null
            }
        }

        fun of(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int): JDate? {
            return JDate(LocalDateTime.of(year, month, day, hour, minute, second))
        }
    }

    constructor() : this(Date().toInstant())

    constructor(date: Date) : this(date.toInstant())

    constructor(calendar: Calendar) : this(calendar.time.toInstant())

    constructor(localDateTime: LocalDateTime) : this(localDateTime.atZone(ZoneId.systemDefault()).toInstant())

    fun toDate(): Date = Date.from(date)

    fun toCalendar(): Calendar = Calendar.getInstance().apply { time = toDate() }

    fun toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(date, ZoneId.systemDefault())

    fun addSeconds(seconds: Long) = JDate(toLocalDateTime().plusSeconds(seconds))

    fun addMinutes(minutes: Long) = JDate(toLocalDateTime().plusMinutes(minutes))

    fun addHours(hours: Long) = JDate(toLocalDateTime().plusHours(hours))

    fun addDays(days: Long) = JDate(toLocalDateTime().plusDays(days))

    fun addMonths(months: Long) = JDate(toLocalDateTime().plusMonths(months))

    fun addYears(years: Long) = JDate(toLocalDateTime().plusYears(years))

    val second get() = toLocalDateTime().second

    val minute get() = toLocalDateTime().minute

    val hour get() = toLocalDateTime().hour

    val day get() = toLocalDateTime().dayOfMonth

    val month get() = toLocalDateTime().monthValue

    val year get() = toLocalDateTime().year

    fun isBefore(jDate: JDate) = toLocalDateTime().isBefore(jDate.toLocalDateTime())

    fun isBefore(date: Date) = isBefore(JDate(date))

    fun isBefore(localDateTime: LocalDateTime) = isBefore(JDate(localDateTime))

    fun isBefore(calendar: Calendar) = isBefore(JDate(calendar))

    fun isAfter(jDate: JDate) = toLocalDateTime().isAfter(jDate.toLocalDateTime())

    fun isAfter(date: Date) = isAfter(JDate(date))

    fun isAfter(localDateTime: LocalDateTime) = isAfter(JDate(localDateTime))

    fun isAfter(calendar: Calendar) = isAfter(JDate(calendar))

    operator fun compareTo(jDate: JDate): Int {
        return when {
            isBefore(jDate) -> -1
            isAfter(jDate) -> 1
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
        other as JDate
        if (date != other.date) return false
        return true
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }
}