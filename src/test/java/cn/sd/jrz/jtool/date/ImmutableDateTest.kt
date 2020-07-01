package cn.sd.jrz.jtool.date

import org.junit.Test
import java.time.LocalDateTime
import java.util.*

/**
 * @author 江荣展
 * @date 2020/6/30
 */
class ImmutableDateTest {

    @Test
    fun of() {
        println(ImmutableDate.of("2014-05-06 13:00:00"))
        println(ImmutableDate.of("abc"))
        println(ImmutableDate.of("2005年9月15日 04时41分20秒", "yyyy年MM月dd日 HH时mm分ss秒"))
        println(ImmutableDate.of(2014, 5, 6, 13, 0, 0))
    }

    @Test
    fun constructor() {
        println(ImmutableDate())
        println(ImmutableDate(Date()))
        println(ImmutableDate(Calendar.getInstance()))
        println(ImmutableDate(LocalDateTime.now()))
    }

    @Test
    fun toDate() {
        val date = ImmutableDate()
        println(date)
        println(date.toDate())
    }

    @Test
    fun toCalendar() {
        val date = ImmutableDate()
        println(date)
        println(date.toCalendar())
    }

    @Test
    fun toLocalDateTime() {
        val date = ImmutableDate()
        println(date)
        println(date.toLocalDateTime())
    }

    @Test
    fun addSeconds() {
        val date = ImmutableDate()
        println(date)
        println(date.addSeconds(1))
    }

    @Test
    fun addMinutes() {
        val date = ImmutableDate()
        println(date)
        println(date.addMinutes(1))
    }

    @Test
    fun addHours() {
        val date = ImmutableDate()
        println(date)
        println(date.addHours(1))
    }

    @Test
    fun addDays() {
        val date = ImmutableDate()
        println(date)
        println(date.addDays(1))
    }

    @Test
    fun addMonths() {
        val date = ImmutableDate.of("2020-01-31 00:00:00")
        println(date)
        println(date?.addMonths(1))
    }

    @Test
    fun addYears() {
        val date = ImmutableDate()
        println(date)
        println(date.addYears(1))
    }

    @Test
    fun second() {
        println(ImmutableDate().second)
    }

    @Test
    fun minute() {
        println(ImmutableDate().minute)
    }

    @Test
    fun hour() {
        println(ImmutableDate().hour)
    }

    @Test
    fun day() {
        println(ImmutableDate().day)
    }

    @Test
    fun month() {
        println(ImmutableDate().month)
    }

    @Test
    fun year() {
        println(ImmutableDate().year)
    }

    @Test
    fun isBefore() {
        val date = ImmutableDate()
        val tomorrow = date.addDays(1)
        val yesterday = date.addDays(-1)
        println(date.isBefore(tomorrow))
        println(date.isBefore(tomorrow.toDate()))
        println(date.isBefore(tomorrow.toCalendar()))
        println(date.isBefore(tomorrow.toLocalDateTime()))
        println(date.isBefore(date))
        println(date.isBefore(date.toDate()))
        println(date.isBefore(date.toCalendar()))
        println(date.isBefore(date.toLocalDateTime()))
        println(date.isBefore(yesterday))
        println(date.isBefore(yesterday.toDate()))
        println(date.isBefore(yesterday.toCalendar()))
        println(date.isBefore(yesterday.toLocalDateTime()))
    }

    @Test
    fun isAfter() {
        val date = ImmutableDate()
        val tomorrow = date.addDays(1)
        val yesterday = date.addDays(-1)
        println(date.isAfter(tomorrow))
        println(date.isAfter(tomorrow.toDate()))
        println(date.isAfter(tomorrow.toCalendar()))
        println(date.isAfter(tomorrow.toLocalDateTime()))
        println(date.isAfter(date))
        println(date.isAfter(date.toDate()))
        println(date.isAfter(date.toCalendar()))
        println(date.isAfter(date.toLocalDateTime()))
        println(date.isAfter(yesterday))
        println(date.isAfter(yesterday.toDate()))
        println(date.isAfter(yesterday.toCalendar()))
        println(date.isAfter(yesterday.toLocalDateTime()))
    }

    @Test
    fun compareTo() {
        val date = ImmutableDate()
        val tomorrow = date.addDays(1)
        val yesterday = date.addDays(-1)
        println(date > tomorrow)
        println(date > tomorrow.toDate())
        println(date > tomorrow.toCalendar())
        println(date > tomorrow.toLocalDateTime())
        println(date > date)
        println(date > date.toDate())
        println(date > date.toCalendar())
        println(date > date.toLocalDateTime())
        println(date > yesterday)
        println(date > yesterday.toDate())
        println(date > yesterday.toCalendar())
        println(date > yesterday.toLocalDateTime())
        println(date < tomorrow)
        println(date < tomorrow.toDate())
        println(date < tomorrow.toCalendar())
        println(date < tomorrow.toLocalDateTime())
        println(date < date)
        println(date < date.toDate())
        println(date < date.toCalendar())
        println(date < date.toLocalDateTime())
        println(date < yesterday)
        println(date < yesterday.toDate())
        println(date < yesterday.toCalendar())
        println(date < yesterday.toLocalDateTime())
    }

    @Test
    fun millis() {
        println(ImmutableDate().millis)
    }

    @Test
    fun testToString() {
        println(ImmutableDate())
    }

    @Test
    fun testEquals() {
        val date = ImmutableDate()
        val tomorrow = date.addDays(1)
        val now = tomorrow.addDays(-1)
        println(date == tomorrow)
        println(date == now)
    }

    @Test
    fun testHashCode() {
        val jDate = ImmutableDate()
        println(jDate.hashCode())
        println(jDate.hashCode())
        println(ImmutableDate().hashCode())
    }
}
