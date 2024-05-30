package com.feryalcodes.maintenanceapp;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    public static MyApp mInstance;
//    public static AERoomDatabase roomDatabase;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        roomDatabase = AERoomDatabase.getDatabase(this);
    }

    public static synchronized MyApp getInstance() {
        return mInstance;
    }
}
