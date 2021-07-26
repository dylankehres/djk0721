package com.toolStoreDemo.tables.calendar;

import com.toolStoreDemo.model.calendar.RecurrencePattern;
import com.toolStoreDemo.tables.base.FirebaseDAO;

/**
 * Data Access Service used to manage Event database records in Firebase
 */
public class RecurrencePatternDAS extends FirebaseDAO<RecurrencePattern> implements RecurrencePatternDAO {
    // Table name in Firebase
    static final String collection = "recurrencePattern";

    public RecurrencePatternDAS() {
        super(collection, RecurrencePattern.class);
    }
}
