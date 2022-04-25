package edu.neu.madcourse.numad22sp_team36_news.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import edu.neu.madcourse.numad22sp_team36_news.model.NewsResponse;
import edu.neu.madcourse.numad22sp_team36_news.repository.NewsRepository;

public class SearchViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> searchInput;

    public SearchViewModel(NewsRepository repository) {
        this.repository = repository;
        this.searchInput = new MutableLiveData<>();
    }

    public void setSearchInput(String query) {
        searchInput.setValue(query);
    }

    public LiveData<NewsResponse> searchNews() {
        return Transformations.switchMap(searchInput, repository::searchNews);
    }
}

