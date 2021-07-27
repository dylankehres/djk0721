package com.toolStoreDemo.services;

import com.toolStoreDemo.model.calendar.Event;
import com.toolStoreDemo.model.calendar.RecurrencePattern;
import com.toolStoreDemo.tables.calendar.EventDAO;
import com.toolStoreDemo.tables.calendar.EventDAS;
import com.toolStoreDemo.tables.calendar.RecurrencePatternDAO;
import com.toolStoreDemo.tables.calendar.RecurrencePatternDAS;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarService {
    private final EventDAO eventDAO;
    private final RecurrencePatternDAO recurrencePatternDAO;

    public CalendarService() {
        // Initialize the necessary data access objects
        this.eventDAO = new EventDAS();
        this.recurrencePatternDAO = new RecurrencePatternDAS();
    }

    /**
     * Add a holiday to the calendar that takes place on the same date each year
     * @param day Day of the month (1-31)
     * @param month Month of the year (1-12)
     * @param startYear Year to begin adding the holiday (yyyy)
     * @param numberOfYears Number of years that the holiday should be added to the calendar
     * @param observeWeekday The holiday will be observed on the nearest weekday if it falls on a weekend
     * @return RecurrencePattern created for the holiday
     */
    public RecurrencePattern insertHolidayOnDate(int day, int month, int startYear, int numberOfYears, boolean observeWeekday) {
        RecurrencePattern recurrencePattern = new RecurrencePattern(RecurrencePattern.RecurrenceFrequency.Yearly, RecurrencePattern.RecurrencePatternType.Date, day, 0, month);
        recurrencePattern = this.recurrencePatternDAO.insert(recurrencePattern);

        LocalDate holidayDate = LocalDate.of(startYear, month, day);

        for (int i = 0; i < numberOfYears; i++) {
            Event holiday = new Event(holidayDate.getDayOfMonth(), holidayDate.getMonthValue(), holidayDate.getYear(), true, observeWeekday, recurrencePattern.getID());
            eventDAO.insert(holiday);

            holidayDate = holidayDate.plusYears(1);
        }

        return recurrencePattern;
    }

    /**
     * Add a holiday to the calendar that takes place on a given day and week each year
     * @param day Day of the week (1-7)
     * @param week Week that the holiday falls on (1-5)
     * @param month Month of the year (1-12)
     * @param startYear Year to begin adding the holiday (yyyy)
     * @param numberOfYears Number of years that the holiday should be added to the calendar
     * @return RecurrencePattern created for the holiday
     */
    public RecurrencePattern insertHolidayOnDayOfMonth(int day, int week, int month, int startYear, int numberOfYears) {
        RecurrencePattern recurrencePattern = new RecurrencePattern(RecurrencePattern.RecurrenceFrequency.Yearly, RecurrencePattern.RecurrencePatternType.DayOfMonth, day, week, month);
        recurrencePattern = this.recurrencePatternDAO.insert(recurrencePattern);

        int year = startYear;

        for (int i = 0; i < numberOfYears; i++) {
            LocalDate date = LocalDate.of(year, month, 1); // Start at the beginning of the month
            int diffDays = date.getDayOfWeek().getValue() - day; // Get week days until the appropriate day of the week
            date = date.plusDays(7 - diffDays); // Move forward to the correct day of the week
            date = date.plusWeeks(week - 1); // Adjust for which week the event should take place

            Event holiday = new Event(date.getDayOfMonth(), month, year, true, false, recurrencePattern.getID());
            eventDAO.insert(holiday);

            year++;
        }

        return recurrencePattern;
    }

    /**
     * Deletes all recurrences of an event
     * @param recurrencePatternId Primary key of RecurrencePattern to be deleted
     */
    public void deleteRecurrenceAndEvents(String recurrencePatternId) {
        ArrayList<Event> events = this.eventDAO.selectEventsByRecurrencePatternId(recurrencePatternId);

        for (Event event : events) {
            this.eventDAO.deleteById(event.getID());
        }

        this.recurrencePatternDAO.deleteById(recurrencePatternId);
    }
}
