package edu.neu.madcourse.numad22sp_team36_tinnews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.neu.madcourse.numad22sp_team36_tinnews.R;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsRepository;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String COUNTRY = "us";
        final String TAG = "HomeFragment";

        NewsRepository repository = new NewsRepository();
        viewModel = new HomeViewModel(repository);
        viewModel.setCountryInput(COUNTRY);
        viewModel.getTopHeadlines().observe(
                getViewLifecycleOwner(),
                newsResponse -> {
                    if (newsResponse != null) {
                        Log.d(TAG, newsResponse.toString());
                    }
                }
        );
    }
}