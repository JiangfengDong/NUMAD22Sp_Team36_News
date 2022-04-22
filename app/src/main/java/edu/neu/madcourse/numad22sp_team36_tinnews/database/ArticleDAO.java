package edu.neu.madcourse.numad22sp_team36_tinnews.database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_tinnews.model.Article;

public interface ArticleDAO {

    @Insert
    void saveArticle(Article article);

    @Query("SELECT * FROM article")
    LiveData<List<Article>> getAllArticles();

    @Delete
    void deleteArticle(Article article);
}
