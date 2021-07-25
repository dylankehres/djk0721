package com.toolStoreDemo.tables.products;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.toolStoreDemo.model.products.Tool;
import com.toolStoreDemo.tables.base.FirebaseDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Data Access Service used to manage Tool database records
 */
public class ToolDAS extends FirebaseDAO<Tool> implements ToolDAO{
    // Table name in Firebase
    static final String collection = "tool";

    public ToolDAS() {
        super(collection, Tool.class);
    }

    @Override
    public Tool insert(String id, Tool tool) {
        Tool existingTool = selectByCode(tool.getCode());

        if(existingTool == null) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(id).set(tool);

            try {
                collectionApiFuture.get();
                if (collectionApiFuture.isDone()) {
                    return tool;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return existingTool;
    }

    @Override
    public Tool selectByCode(String toolCode) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("code", toolCode).get();
        ArrayList<Tool> tools = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    tools.add(queryDocumentSnapshot.toObject(Tool.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Get the first record if it exists, the name should always be unique
        return tools.isEmpty() ? null : tools.get(0);
    }

    @Override
    public ArrayList<Tool> selectByBrandKey(String brandKey) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("brandKey", brandKey).get();
        ArrayList<Tool> tools = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    tools.add(queryDocumentSnapshot.toObject(Tool.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return tools;
    }

    @Override
    public ArrayList<Tool> selectByTypeKey(String typeKey) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("typeKey", typeKey).get();
        ArrayList<Tool> tools = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    tools.add(queryDocumentSnapshot.toObject(Tool.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return tools;
    }
}
