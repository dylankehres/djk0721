package com.toolStoreDemo.tables.products;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.toolStoreDemo.model.products.ToolType;
import com.toolStoreDemo.tables.base.FirebaseDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Data Access Service used to manage ToolType database records in Firebase
 */
public class ToolTypeDAS extends FirebaseDAO<ToolType> implements ToolTypeDAO{
    // Table name in Firebase
    static final String collection = "toolType";

    public ToolTypeDAS() {
        super(collection, ToolType.class);
    }

    /**
     * Inserts a ToolType unless a type with the given name already exists
     * @param id Unique ID for the record
     * @param toolType ToolType to add to the database
     * @return ToolType that was inserted or already existed
     */
    @Override
    public ToolType insert(String id, ToolType toolType) {
        ToolType existingType = selectByName(toolType.getTypeName());

        if(existingType == null) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(id).set(toolType);

            try {
                collectionApiFuture.get();
                if (collectionApiFuture.isDone()) {
                    return toolType;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return existingType;
    }

    /**
     * Selects a ToolType with the given name
     * @param name Name of the tool type
     * @return ToolType with the given name or null if one did not exist
     */
    public ToolType selectByName(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("typeName", name).get();
        ArrayList<ToolType> toolTypes = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    toolTypes.add(queryDocumentSnapshot.toObject(ToolType.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Get the first record if it exists, the name should always be unique
        return toolTypes.isEmpty() ? null : toolTypes.get(0);
    }
}
