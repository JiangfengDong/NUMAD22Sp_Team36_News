package edu.neu.madcourse.numad22sp_team36_tinnews.repository;

import edu.neu.madcourse.numad22sp_team36_tinnews.network.NewsAPI;
import edu.neu.madcourse.numad22sp_team36_tinnews.network.RetrofitClient;

public class NewsRepository {
    private final NewsAPI newsAPI;

    public NewsRepository() {
        newsAPI = RetrofitClient.newInstance().create(NewsAPI.class);
    }
}
