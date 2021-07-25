package com.toolStoreDemo.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInit {
    // Name of the Firebase Project used for the Firestore
    private static String projectName = "toolstoredemo";

    public static void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("./toolstoredemo-firebase-adminsdk-wuhbw-d1f4497f0d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://" + projectName + ".firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
