package edu.neu.madcourse.numad22sp_team36_news.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_news.model.Article;
import edu.neu.madcourse.numad22sp_team36_news.model.NewsResponse;
import edu.neu.madcourse.numad22sp_team36_news.repository.NewsRepository;

public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput;
    private final MutableLiveData<String> recommendedArticles;


    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
        this.countryInput = new MutableLiveData<>();
        this.recommendedArticles = new MutableLiveData<>();
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    //Get the news from the save page.
    public LiveData<List<Article>> getAllFavoriteArticles() {
        return repository.getAllSavedArticles();
    }

    public void setRecommendedArticles(String recommended) {
        recommendedArticles.setValue(recommended);
    }

    //Get recommendedArticles.
    public LiveData<NewsResponse> getRecommendedArticles() {
        return Transformations.switchMap(recommendedArticles, repository::getRecommendedArticles);
    }

}
