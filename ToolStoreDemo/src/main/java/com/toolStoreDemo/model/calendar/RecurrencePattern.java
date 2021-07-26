package com.toolStoreDemo.model.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toolStoreDemo.model.BaseModel;

public class RecurrencePattern extends BaseModel {
    /**
     * Frequency that the event occurs represented by the ordinal of {@link RecurrenceFrequency}
     */
    @JsonProperty("recurrenceFrequency")
    private int recurrenceFrequency;

    /**
     * Pattern in which the event occurs represented by the ordinal of {@link RecurrencePatternType}
     */
    @JsonProperty("recurrencePatternType")
    private int recurrencePatternType;

    /**
     * Day that the event reoccurs. If recurrencePatternType is date use day of month (1-31),
     * if recurrencePatternType is DayOfMonth use the numeric day of the week 0-Monday to 6-Sunday
     */
    @JsonProperty("recurrenceDay")
    private int recurrenceDay;

    /**
     * Numeric week that the event will occur in the month.
     * Only used if recurrencePatternType is DayOfMonth
     */
    @JsonProperty("recurrenceWeek")
    private int recurrenceWeek;

    /**
     * Numeric month that the event will occur in the year 1-January to 12-December.
     */
    @JsonProperty("recurrenceMonth")
    private int recurrenceMonth;

    public enum RecurrenceFrequency {
        Never,
        Daily,
        Weekly,
        Monthly,
        Yearly
    }

    public enum RecurrencePatternType {
        Date,
        DayOfMonth
    }

    public RecurrencePattern() {
        this.recurrenceFrequency = RecurrenceFrequency.Never.ordinal();
        this.recurrencePatternType = RecurrencePatternType.Date.ordinal();
        this.recurrenceDay = 0;
        this.recurrenceWeek = 0;
        this.recurrenceMonth = 0;
    }

    public RecurrencePattern(RecurrenceFrequency frequency, RecurrencePatternType patternType, int recurrenceDay, int recurrenceWeek, int recurrenceMonth) {
        this.recurrenceFrequency = frequency.ordinal();
        this.recurrencePatternType = patternType.ordinal();
        this.recurrenceDay = recurrenceDay;
        this.recurrenceWeek = recurrenceWeek;
        this.recurrenceMonth = recurrenceMonth;
    }
}
