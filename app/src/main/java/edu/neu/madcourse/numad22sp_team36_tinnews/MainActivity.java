package edu.neu.madcourse.numad22sp_team36_tinnews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.neu.madcourse.numad22sp_team36_tinnews.model.NewsResponse;
import edu.neu.madcourse.numad22sp_team36_tinnews.network.NewsAPI;
import edu.neu.madcourse.numad22sp_team36_tinnews.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        // Test Request
        NewsAPI newsAPI = RetrofitClient.newInstance().create(NewsAPI.class);
        newsAPI.getTopHeadlines("US").enqueue(new Callback<NewsResponse>() {
            private final String TAG = "getTopHeadlines";

            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                final String MSG = response.isSuccessful() ? response.body().toString() : response.toString();
                Log.d(TAG, MSG);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                final String MSG = t.toString();
                Log.d(TAG, MSG);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}