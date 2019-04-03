package cn.sd.jrz.jtool.immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author JRZ
 * @since 1.0
 */
public final class JrzDate {
    private final Instant date;

    public static JrzDate of(String date) {
        return of(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static JrzDate of(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date d;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (d == null) {
            return null;
        }
        return new JrzDate(d);
    }

    public static JrzDate of(int year, int month, int day, int hour, int minute, int second) {
        return new JrzDate(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    public JrzDate() {
        this.date = new Date().toInstant();
    }

    public JrzDate(Date date) {
        this.date = date.toInstant();
    }

    public JrzDate(Calendar calendar) {
        this.date = calendar.getTime().toInstant();
    }

    public JrzDate(LocalDateTime localDateTime) {
        this.date = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public final Date toDate() {
        return Date.from(date);
    }

    public final LocalDateTime toLocalDateTime() {
        return LocalDateTime.ofInstant(date, ZoneId.systemDefault());
    }

    public final Calendar toCalendar() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(toDate());
        return instance;
    }

    public final JrzDate addSeconds(long seconds) {
        return new JrzDate(toLocalDateTime().plusSeconds(seconds));
    }

    public final JrzDate addMinutes(long minutes) {
        return new JrzDate(toLocalDateTime().plusMinutes(minutes));
    }

    public final JrzDate addHours(long hours) {
        return new JrzDate(toLocalDateTime().plusHours(hours));
    }

    public final JrzDate addDays(long days) {
        return new JrzDate(toLocalDateTime().plusDays(days));
    }

    public final JrzDate addMonths(long months) {
        return new JrzDate(toLocalDateTime().plusMonths(months));
    }

    public final JrzDate addYears(long years) {
        return new JrzDate(toLocalDateTime().plusYears(years));
    }

    public final int second() {
        return toLocalDateTime().getSecond();
    }

    public final int minute() {
        return toLocalDateTime().getMinute();
    }

    public final int hour() {
        return toLocalDateTime().getHour();
    }

    public final int day() {
        return toLocalDateTime().getDayOfMonth();
    }

    public final int month() {
        return toLocalDateTime().getMonthValue();
    }

    public final int year() {
        return toLocalDateTime().getYear();
    }

    public final boolean isBefore(JrzDate jrzDate) {
        return toLocalDateTime().isBefore(jrzDate.toLocalDateTime());
    }

    public final boolean isBefore(Date date) {
        return isBefore(new JrzDate(date));
    }

    public final boolean isBefore(LocalDateTime localDateTime) {
        return isBefore(new JrzDate(localDateTime));
    }

    public final boolean isBefore(Calendar calendar) {
        return isBefore(new JrzDate(calendar));
    }

    public final boolean isAfter(JrzDate jrzDate) {
        return toLocalDateTime().isAfter(jrzDate.toLocalDateTime());
    }

    public final boolean isAfter(Date date) {
        return isAfter(new JrzDate(date));
    }

    public final boolean isAfter(LocalDateTime localDateTime) {
        return isAfter(new JrzDate(localDateTime));
    }

    public final boolean isAfter(Calendar calendar) {
        return isAfter(new JrzDate(calendar));
    }

    public final long currentTimeMillis() {
        return toDate().getTime();
    }

    @Override
    public final String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(toDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JrzDate jrzDate = (JrzDate) o;
        return Objects.equals(date, jrzDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
