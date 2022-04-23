package edu.neu.madcourse.numad22sp_team36_tinnews.ui.save;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_tinnews.model.Article;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsRepository;

public class SaveViewModel extends ViewModel {

    private final NewsRepository repository;

    public SaveViewModel(NewsRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Article>> getAllSavedArticles() {
        return repository.getAllSavedArticles();
    }

    public void deleteSavedArticle(Article article) {
        repository.deleteSavedArticle(article);
    }
}
