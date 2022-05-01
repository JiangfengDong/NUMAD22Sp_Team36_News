package edu.neu.madcourse.numad22sp_team36_news.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_news.model.Article;

//Encapsulates a class of livedata results. This is achieved by extending MediatorLiveData.
public class MyLiveData extends MediatorLiveData<Boolean> {
    public List<Article> savedArticles;
    public int finishCount;

    public void addSavedArticlesSource(LiveData<List<Article>> source) {
        addSource(source, (Observer<List<Article>>) articlesResult -> {
            savedArticles = articlesResult;   //Store articles from the savePage
            finishCount++;
            postValue(finishCount == 1);
        });
    }
}
