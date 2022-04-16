package edu.neu.madcourse.numad22sp_team36_tinnews;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class TinNewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}