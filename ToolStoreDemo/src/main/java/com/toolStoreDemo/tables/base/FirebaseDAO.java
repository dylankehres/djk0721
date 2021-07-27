package com.toolStoreDemo.tables.base;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.toolStoreDemo.model.BaseModel;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Base class for a Firebase Data Access Service
 * @param <T> Model being accessed by this service
 */
public class FirebaseDAO<T extends BaseModel> implements BaseDAO<T> {
    private final String collection;
    private final Class<T> modelClass;

    /**
     * Creates a generic Data Access Object instance for the given collection and model
     * @param collection Name of the table in Firebase
     * @param modelClass Class of the model being accessed
     */
    public FirebaseDAO(String collection, Class<T> modelClass) {
        this.collection = collection;
        this.modelClass = modelClass;
    }

    /**
     * Inserts a model into the database with a random UUID
     * @param model Model to insert into the database
     * @return Model that was inserted into the database
     */
    @Override
    public T insert(T model) {
        UUID id = UUID.randomUUID();
        model.setID(id.toString());
        return insert(id.toString(), model);
    }

    /**
     * Inserts a model into the database with a given ID
     * @param id Unique ID for the record
     * @param model Model to insert into the database
     * @return Model that was inserted into the database
     */
    @Override
    public T insert(String id, T model) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(id).set(model);

        try {
            collectionApiFuture.get();
            if (collectionApiFuture.isDone()) {
                return model;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes record with the given key
     * @param id Primary key of the record to delete
     * @return Deletion was successful
     */
    @Override
    public boolean deleteById(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> deleteFuture = dbFirestore.collection(collection).document(id).delete();

        try {
            deleteFuture.get();
            if (deleteFuture.isDone()) {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Applies updates to a record already in the database
     * @param model Model to update
     * @return Updated model
     */
    @Override
    public T update(T model) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(model.getID()).set(model);

        try {
            collectionApiFuture.get();
            if (collectionApiFuture.isDone()) {
                return model;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Select all records in the collection
     * @return ArrayList of all models
     */
    @Override
    public ArrayList<T> selectAll() {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).get();
        ArrayList<T> selectedModels = new ArrayList<>();

        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                selectedModels.add(document.toObject(this.modelClass));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return selectedModels;
    }

    /**
     * Select a record with the given primary key
     * @param id Primary key of the record to select
     * @return Selected model or null if it does not exist
     */
    @Override
    public T selectById(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(collection).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(document != null && document.exists()){
            return document.toObject(this.modelClass);
        }

        return null;
    }
}
