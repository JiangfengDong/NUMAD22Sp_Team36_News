package edu.neu.madcourse.numad22sp_team36_news;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.neu.madcourse.numad22sp_team36_news.notification.NotificationService;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Intent notificationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (notificationIntent != null) {
            stopService(notificationIntent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        notificationIntent = new Intent(this, NotificationService.class);
        startService(notificationIntent);
    }
}