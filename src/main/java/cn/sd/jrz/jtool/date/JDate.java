package cn.sd.jrz.jtool.date;

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
public final class JDate {
    private final Instant date;

    public static JDate of(String date) {
        return of(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static JDate of(String date, String format) {
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
        return new JDate(d);
    }

    public static JDate of(int year, int month, int day, int hour, int minute, int second) {
        return new JDate(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    public JDate() {
        this.date = new Date().toInstant();
    }

    public JDate(Date date) {
        this.date = date.toInstant();
    }

    public JDate(Calendar calendar) {
        this.date = calendar.getTime().toInstant();
    }

    public JDate(LocalDateTime localDateTime) {
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

    public final JDate addSeconds(long seconds) {
        return new JDate(toLocalDateTime().plusSeconds(seconds));
    }

    public final JDate addMinutes(long minutes) {
        return new JDate(toLocalDateTime().plusMinutes(minutes));
    }

    public final JDate addHours(long hours) {
        return new JDate(toLocalDateTime().plusHours(hours));
    }

    public final JDate addDays(long days) {
        return new JDate(toLocalDateTime().plusDays(days));
    }

    public final JDate addMonths(long months) {
        return new JDate(toLocalDateTime().plusMonths(months));
    }

    public final JDate addYears(long years) {
        return new JDate(toLocalDateTime().plusYears(years));
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

    public final boolean isBefore(JDate jDate) {
        return toLocalDateTime().isBefore(jDate.toLocalDateTime());
    }

    public final boolean isBefore(Date date) {
        return isBefore(new JDate(date));
    }

    public final boolean isBefore(LocalDateTime localDateTime) {
        return isBefore(new JDate(localDateTime));
    }

    public final boolean isBefore(Calendar calendar) {
        return isBefore(new JDate(calendar));
    }

    public final boolean isAfter(JDate jDate) {
        return toLocalDateTime().isAfter(jDate.toLocalDateTime());
    }

    public final boolean isAfter(Date date) {
        return isAfter(new JDate(date));
    }

    public final boolean isAfter(LocalDateTime localDateTime) {
        return isAfter(new JDate(localDateTime));
    }

    public final boolean isAfter(Calendar calendar) {
        return isAfter(new JDate(calendar));
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
        JDate jDate = (JDate) o;
        return Objects.equals(date, jDate.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
