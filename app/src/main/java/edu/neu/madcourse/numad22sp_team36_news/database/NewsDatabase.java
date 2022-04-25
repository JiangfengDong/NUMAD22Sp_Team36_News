package edu.neu.madcourse.numad22sp_team36_news.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.neu.madcourse.numad22sp_team36_news.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();
}