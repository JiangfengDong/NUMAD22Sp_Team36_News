package edu.neu.madcourse.numad22sp_team36_tinnews.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import edu.neu.madcourse.numad22sp_team36_tinnews.model.NewsResponse;
import edu.neu.madcourse.numad22sp_team36_tinnews.network.NewsAPI;
import edu.neu.madcourse.numad22sp_team36_tinnews.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private final NewsAPI newsAPI;

    public NewsRepository() {
        newsAPI = RetrofitClient.newInstance().create(NewsAPI.class);
    }

    public LiveData<NewsResponse> getTopHeadlines(String country) {
        MutableLiveData<NewsResponse> topHeadlinesLiveData = new MutableLiveData<>();
        newsAPI.getTopHeadlines(country).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    topHeadlinesLiveData.setValue(response.body());
                } else {
                    topHeadlinesLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                topHeadlinesLiveData.setValue(null);
            }
        });
        return topHeadlinesLiveData;
    }

    public LiveData<NewsResponse> searchNews(String query) {
        final int PAGE_SIZE = 40;
        MutableLiveData<NewsResponse> everyThingLiveData = new MutableLiveData<>();
        newsAPI.getEverything(query, PAGE_SIZE).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    everyThingLiveData.setValue(response.body());
                } else {
                    everyThingLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                everyThingLiveData.setValue(null);
            }
        });
        return everyThingLiveData;
    }
}
