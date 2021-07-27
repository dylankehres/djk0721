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
    private EventDAO eventDAO;
    private RecurrencePatternDAO recurrencePatternDAO;

    public CalendarService() {
        // Initialize the necessary data access objects
        this.eventDAO = new EventDAS();
        this.recurrencePatternDAO = new RecurrencePatternDAS();
    }

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

    public void deleteRecurrenceAndEvents(String recurrencePatternKey) {
        ArrayList<Event> events = this.eventDAO.selectEventsByRecurrencePatternId(recurrencePatternKey);

        for (Event event : events) {
            this.eventDAO.deleteById(event.getID());
        }

        this.recurrencePatternDAO.deleteById(recurrencePatternKey);
    }
}
