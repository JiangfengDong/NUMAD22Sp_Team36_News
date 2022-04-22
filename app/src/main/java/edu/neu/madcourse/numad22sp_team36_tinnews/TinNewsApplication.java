package edu.neu.madcourse.numad22sp_team36_tinnews;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import edu.neu.madcourse.numad22sp_team36_tinnews.database.TinNewsDatabase;

public class TinNewsApplication extends Application {

    private static final String DATABASE_FILE_NAME = "tinnews_db";

    private static TinNewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(this, TinNewsDatabase.class, DATABASE_FILE_NAME).build();
    }

    public static TinNewsDatabase getDatabase() {
        return database;
    }
}