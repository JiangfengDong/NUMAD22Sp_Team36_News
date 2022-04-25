package edu.neu.madcourse.numad22sp_team36_news;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import edu.neu.madcourse.numad22sp_team36_news.database.NewsDatabase;

public class NewsApplication extends Application {

    private static final String ROOM_DATABASE_FILE_NAME = "news_db";

    private static NewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        database = Room.databaseBuilder(this, NewsDatabase.class, ROOM_DATABASE_FILE_NAME).build();
    }

    public static NewsDatabase getDatabase() {
        return database;
    }
}