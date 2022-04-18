package edu.neu.madcourse.numad22sp_team36_tinnews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.neu.madcourse.numad22sp_team36_tinnews.databinding.FragmentHomeBinding;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsRepository;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsViewModelFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String COUNTRY = "us";
        final String TAG = "HomeFragment";

        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
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