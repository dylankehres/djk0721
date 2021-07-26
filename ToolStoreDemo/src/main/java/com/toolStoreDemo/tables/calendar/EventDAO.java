package com.toolStoreDemo.tables.calendar;

import com.toolStoreDemo.model.calendar.Event;

import java.util.ArrayList;

/**
 * Data Access Object for Event database records
 */
public interface EventDAO {
    Event insert(String id, Event event);

    Event insert(Event event);

    boolean deleteById(String id);

    Event update(Event event);

    ArrayList<Event> selectAll();

    Event selectById(String id);
}
