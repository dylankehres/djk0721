package com.toolStoreDemo.tables.calendar;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.toolStoreDemo.model.calendar.Event;
import com.toolStoreDemo.tables.base.FirebaseDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Data Access Service used to manage Event database records in Firebase
 */
public class EventDAS extends FirebaseDAO<Event> implements EventDAO {
    // Table name in Firebase
    static final String collection = "event";

    public EventDAS() {
        super(collection, Event.class);
    }

    @Override
    public ArrayList<Event> selectEventsByRecurrencePatternId(String recurrencePatternId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("recurrencePatternKey", recurrencePatternId).get();
        ArrayList<Event> events = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    events.add(queryDocumentSnapshot.toObject(Event.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return events;
    }

    @Override
    public ArrayList<Event> selectAllHolidays() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("holiday", true).get();
        ArrayList<Event> events = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    events.add(queryDocumentSnapshot.toObject(Event.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return events;
    }
}
