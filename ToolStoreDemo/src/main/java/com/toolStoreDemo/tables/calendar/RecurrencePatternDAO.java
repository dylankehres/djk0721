package com.toolStoreDemo.tables.calendar;

import com.toolStoreDemo.model.calendar.RecurrencePattern;

import java.util.ArrayList;

/**
 * Data Access Object for RecurrencePattern database records
 */
public interface RecurrencePatternDAO {
    RecurrencePattern insert(String id, RecurrencePattern recurrencePattern);

    RecurrencePattern insert(RecurrencePattern recurrencePattern);

    boolean deleteById(String id);

    RecurrencePattern update(RecurrencePattern recurrencePattern);

    ArrayList<RecurrencePattern> selectAll();

    RecurrencePattern selectById(String id);
}
