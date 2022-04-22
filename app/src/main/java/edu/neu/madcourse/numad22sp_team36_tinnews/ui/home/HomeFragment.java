package edu.neu.madcourse.numad22sp_team36_tinnews.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_tinnews.databinding.FragmentHomeBinding;
import edu.neu.madcourse.numad22sp_team36_tinnews.model.Article;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsRepository;
import edu.neu.madcourse.numad22sp_team36_tinnews.repository.NewsViewModelFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    private CardStackLayoutManager layoutManager;
    private List<Article> articles;

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

        CardSwipeAdapter swipeAdapter = new CardSwipeAdapter();
        layoutManager = new CardStackLayoutManager(requireContext());
        layoutManager.setStackFrom(StackFrom.Top);
        binding.homeCardStackView.setLayoutManager(layoutManager);
        binding.homeCardStackView.setAdapter(swipeAdapter);

        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
        viewModel.setCountryInput(getResources().getConfiguration().locale.getCountry());
        viewModel.getTopHeadlines().observe(
                getViewLifecycleOwner(),
                newsResponse -> {
                    if (newsResponse != null) {
                        articles = newsResponse.articles;
                        swipeAdapter.setArticles(articles);
                    }
                }
        );
    }

    private void swipeCard(Direction direction) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder().setDirection(direction).setDuration(Duration.Normal.duration).build();
        layoutManager.setSwipeAnimationSetting(setting);
        binding.homeCardStackView.swipe();
    }
}