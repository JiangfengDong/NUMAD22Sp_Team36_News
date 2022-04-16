package edu.neu.madcourse.numad22sp_team36_tinnews.ui.search;

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

public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String QUERY = "Covid-19";
        final String TAG = "SearchFragment";

        NewsRepository repository = new NewsRepository();
        viewModel = new SearchViewModel(repository);
        viewModel.setSearchInput(QUERY);
        viewModel.searchNews().observe(
                getViewLifecycleOwner(),
                newsResponse -> {
                    if (newsResponse != null) {
                        Log.d(TAG, newsResponse.toString());
                    }
                }
        );
    }
}