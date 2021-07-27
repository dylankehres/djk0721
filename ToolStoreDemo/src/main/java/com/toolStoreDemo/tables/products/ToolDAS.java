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
 * Data Access Service used to manage Tool database records in Firebase
 */
public class ToolDAS extends FirebaseDAO<Tool> implements ToolDAO{
    // Table name in Firebase
    static final String collection = "tool";

    public ToolDAS() {
        super(collection, Tool.class);
    }

    /**
     * Inserts a tool unless a tool with the given code already exists
     * @param id Unique ID for the record
     * @param tool Tool to add to the database
     * @return Tool that was inserted or already existed
     */
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

    /**
     * Selects the tool with the given unique code
     * @param toolCode Unique code associated with the tool
     * @return Tool with the given code or null if one did not exist
     */
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

    /**
     * Selects all tools that belong to the brand with the given ID
     * @param brandId Primary key of the ToolBrand
     * @return ArrayList of tools that belong to the brand
     */
    @Override
    public ArrayList<Tool> selectByBrandId(String brandId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("brandId", brandId).get();
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

    /**
     * Selects all tools that are the type with the given ID
     * @param typeId Primary key of the ToolType
     * @return ArrayList of tools that are the given type
     */
    @Override
    public ArrayList<Tool> selectByTypeId(String typeId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("typeId", typeId).get();
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
