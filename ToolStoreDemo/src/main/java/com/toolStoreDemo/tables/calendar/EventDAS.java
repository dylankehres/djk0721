package com.toolStoreDemo.tables.calendar;

import com.toolStoreDemo.model.calendar.Event;
import com.toolStoreDemo.tables.base.FirebaseDAO;

/**
 * Data Access Service used to manage Event database records
 */
public class EventDAS extends FirebaseDAO<Event> implements EventDAO {
    // Table name in Firebase
    static final String collection = "event";

    public EventDAS() {
        super(collection, Event.class);
    }
}
