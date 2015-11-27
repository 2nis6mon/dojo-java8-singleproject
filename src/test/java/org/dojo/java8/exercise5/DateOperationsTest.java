package org.dojo.java8.exercise5;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.dojo.java8.exercise5.tools.TemporalAccessorAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class DateOperationsTest {

    @Test
    public void should_parse_date() {
       assertThat(DateOperations.parseDate("27/01/2014")).isInSameDayAs("2014-01-27");
    }

    @Test
    public void should_parse_date_time() {
        assertThat(DateOperations.parseDateTime("27/01/2014 12:05:10")).
                isInSameDayAs("2014-01-27").
                isWithinHourOfDay(12).
                isWithinHourOfDay(12).
                isWithinSecond(10);
    }

    @Test
    public void should_compute_age() {
        assertThat(computeAgeFor("2013-07-08", "2014-02-06")).isEqualTo(0);
        assertThat(computeAgeFor("2010-10-12", "2014-02-06")).isEqualTo(3);
        assertThat(computeAgeFor("2010-01-28", "2014-02-06")).isEqualTo(4);
    }

    //TODO Change parseDateJava7 to parseDateJava8 for switch to localDate
    private int computeAgeFor(String birthday, String currentDate) {
        return DateOperations.age(parseDateJava7(birthday), parseDateJava7(currentDate));
    }

    @Test
    //TODO Change parseDateJava7 to parseDateJava8 for switch to localDate
    public void should_compute_day_with_time() {
        assertThat(DateOperations.dayDateWithTime(parseDateJava7("2013-07-08"), 15, 12, 3))
                .isInSameDayAs("2013-07-08")
                .isWithinHourOfDay(15)
                .isWithinMinute(12)
                .isWithinSecond(3);
    }

    @Test
    //TODO Change parseDateTimeJava7 to parseDateTimeJava8 for switch to localDate
    public void should_add_duration() {
        assertThat(DateOperations.addDuration(parseDateTimeJava7("2014-01-27T12:05:10"), 162))
                .isInSameDayAs("2014-01-27")
                .isWithinHourOfDay(14)
                .isWithinMinute(47)
                .isWithinSecond(10);
    }

    @Test
    //TODO Change parseDateTimeJava7 to parseDateTimeJava8 for switch to localDate
    public void should_return_true_when_days_are_equals() {
        assertThat(DateOperations.dayAreEquals(parseDateTimeJava7("2015-01-27T12:05:10"), parseDateTimeJava7("2014-01-27T20:05:10"))).isFalse();
        assertThat(DateOperations.dayAreEquals(parseDateTimeJava7("2014-01-27T12:05:10"), parseDateTimeJava7("2014-01-28T12:05:10"))).isFalse();
        assertThat(DateOperations.dayAreEquals(parseDateTimeJava7("2014-01-27T12:05:10"), parseDateTimeJava7("2014-01-27T20:05:10"))).isTrue();
    }

    @Test
    public void should_convert_to_time_zone() {
        String date = DateOperations.convertToTimeZone("18/01/2014 12:00:00", ZoneId.of("Europe/Moscow"), ZoneId.of("America/New_York"));

        assertThat(date).isEqualTo("18/01/2014 03:00:00");

    }

    private LocalDate parseDateJava8(String date) {
        return LocalDate.parse(date);
    }

    private Date parseDateJava7(String java7Date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(java7Date);
        } catch (ParseException e) {
            return null;
        }
    }

    private LocalDateTime parseDateTimeJava8(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }

    private Date parseDateTimeJava7(String dateTime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }
}