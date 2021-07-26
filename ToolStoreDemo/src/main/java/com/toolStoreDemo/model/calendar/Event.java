package com.toolStoreDemo.model.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

import java.util.Calendar;

/**
 * Model used to represent on event on the store calendar
 */
public class Event extends BaseModel {
    /**
     * Number of seconds since January 1, 1970 and the date of the event
     */
    @JsonProperty("dateTime")
    private long dateTime;

    /**
     * The given date is a holiday and may require special pricing
     */
    @JsonProperty("holiday")
    private boolean holiday;

    /**
     * The event is recurring and should be observed on the nearest weekday when it falls on a weekend
     */
    @JsonProperty("observeWeekday")
    private boolean observeWeekday;

    /**
     * Foreign Key for the {@link RecurrencePattern} if this event reoccurs
     */
    @JsonProperty("recurrencePatternKey")
    private String recurrencePatternKey;

    /**
     * Creates a default Event for January 1, 1970
     */
    public Event() {
        super();

        this.dateTime = Calendar.getInstance().getTime().getTime();
        this.holiday = false;
        this.observeWeekday = false;
        this.recurrencePatternKey = "";
    }

    /**
     * Creates an Event on the given date that does not reoccur
     * @param day Numeric day of the month the Event will occur
     * @param month Numeric month that the event will occur from 1- January to 12-December
     * @param year Numeric year that the event will occur
     * @param holiday The event is a holiday
     * @param observeWeekday The event should be observed on the nearest weekday when it falls on a weekend
     */
    public Event(int day, int month, int year, boolean holiday, boolean observeWeekday) {
        super();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        this.dateTime = calendar.getTime().getTime();
        this.holiday = holiday;
        this.observeWeekday = observeWeekday;
        this.recurrencePatternKey = "";
    }

    /**
     * Creates an Event on the given date that does not reoccur
     * @param day Numeric day of the month the Event will occur
     * @param month Numeric month that the event will occur from 1- January to 12-December
     * @param year Numeric year that the event will occur
     * @param holiday The event is a holiday
     * @param observeWeekday The event should be observed on the nearest weekday when it falls on a weekend
     * @param recurrencePatternKey Primary Key of the recurrence pattern this event uses
     */
    public Event(int day, int month, int year, boolean holiday, boolean observeWeekday, String recurrencePatternKey) {
        super();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        this.dateTime = calendar.getTime().getTime();
        this.holiday = holiday;
        this.observeWeekday = observeWeekday;
        this.recurrencePatternKey = recurrencePatternKey;
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    public boolean isObserveWeekday() {
        return observeWeekday;
    }

    public void setObserveWeekday(boolean observeWeekday) {
        this.observeWeekday = observeWeekday;
    }

    public String getRecurrencePatternKey() {
        return recurrencePatternKey;
    }

    public void setRecurrencePatternKey(String recurrencePatternKey) {
        this.recurrencePatternKey = recurrencePatternKey;
    }
}
