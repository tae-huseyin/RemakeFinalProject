package com.theappexperts.remakefinalproject;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by TheAppExperts on 02/01/2018.
 */

public class MyApp extends Application {
    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        Fresco.initialize(getApplicationContext());
    }
}
