package com.toolStoreDemo.tables.products;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.toolStoreDemo.model.products.ToolBrand;
import com.toolStoreDemo.tables.base.FirebaseDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Data Access Service used to manage ToolBrand database records
 */
public class ToolBrandDAS extends FirebaseDAO<ToolBrand> implements ToolBrandDAO {
    // Table name in Firebase
    static final String collection = "toolBrand";

    public ToolBrandDAS() {
        super(collection, ToolBrand.class);
    }

    @Override
    public ToolBrand insert(ToolBrand toolBrand) {
        UUID id = UUID.randomUUID();
        toolBrand.setID(id.toString());
        return insert(id.toString(), toolBrand);
    }

    @Override
    public ToolBrand insert(String id, ToolBrand toolBrand) {
        ToolBrand existingBrand = selectByName(toolBrand.getBrandName());

        if(toolBrand == null) {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collection).document(id).set(toolBrand);

            try {
                collectionApiFuture.get();
                if (collectionApiFuture.isDone()) {
                    return toolBrand;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return existingBrand;
    }

    public ToolBrand selectByName(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = dbFirestore.collection(collection).whereEqualTo("brandName", name).get();
        ArrayList<ToolBrand> toolBrands = new ArrayList<>();

        try {
            List<QueryDocumentSnapshot> docList = querySnapshot.get().getDocuments();
            if(!docList.isEmpty()){
                for (QueryDocumentSnapshot queryDocumentSnapshot : docList) {
                    toolBrands.add(queryDocumentSnapshot.toObject(ToolBrand.class));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Get the first record if it exists, the name should always be unique
        return toolBrands.isEmpty() ? null : toolBrands.get(0);
    }
}
