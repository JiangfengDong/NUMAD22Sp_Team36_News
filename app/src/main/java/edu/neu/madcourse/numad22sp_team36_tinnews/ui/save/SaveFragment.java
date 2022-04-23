package edu.neu.madcourse.numad22sp_team36_tinnews.ui.save;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.neu.madcourse.numad22sp_team36_tinnews.databinding.FragmentSaveBinding;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsRepository;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsViewModelFactory;

public class SaveFragment extends Fragment {

    private FragmentSaveBinding binding;
    private SaveViewModel viewModel;

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSaveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(SaveViewModel.class);
        viewModel.getAllSavedArticles().observe(
                getViewLifecycleOwner(),
                savedArticles -> {
                    if (savedArticles != null) {
                        Log.d("SaveFragment", savedArticles.toString());
                    }
                });
    }
}