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
 * Data Access Service used to manage ToolType database records
 */
public class ToolTypeDAS extends FirebaseDAO<ToolType> implements ToolTypeDAO{
    // Table name in Firebase
    static final String collection = "toolType";

    public ToolTypeDAS() {
        super(collection, ToolType.class);
    }

    @Override
    public ToolType insert(ToolType toolType) {
        UUID id = UUID.randomUUID();
        toolType.setID(id.toString());
        return insert(id.toString(), toolType);
    }

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
