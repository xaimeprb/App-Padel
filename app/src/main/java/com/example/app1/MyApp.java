package com.example.app1;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MyApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        // Inicializa Firebase antes de usar cualquier servicio
        FirebaseApp.initializeApp(this);

    }

}

