package edu.neu.madcourse.numad22sp_team36_news.ui.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.List;

import edu.neu.madcourse.numad22sp_team36_news.MainActivity;
import edu.neu.madcourse.numad22sp_team36_news.R;
import edu.neu.madcourse.numad22sp_team36_news.repository.NewsViewModelFactory;
import edu.neu.madcourse.numad22sp_team36_news.databinding.FragmentHomeBinding;
import edu.neu.madcourse.numad22sp_team36_news.model.Article;
import edu.neu.madcourse.numad22sp_team36_news.repository.NewsRepository;

public class HomeFragment extends Fragment implements CardStackListener {

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
        layoutManager = new CardStackLayoutManager(requireContext(), this);
        layoutManager.setStackFrom(StackFrom.Top);
        binding.homeCardStackView.setLayoutManager(layoutManager);
        binding.homeCardStackView.setAdapter(swipeAdapter);

        NewsRepository repository = new NewsRepository();
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
        viewModel.setCountryInput(getResources().getConfiguration().locale.getCountry());

        //Retrieve the favorite articles collected by the user in the save page from the database
        MyLiveData liveDataManager = new MyLiveData();
        liveDataManager.addSavedArticlesSource(viewModel.getAllFavoriteArticles());

        //Recommend relevant favorite articles according to the first five fields of the article content and
        // If there is no saved articles, recommend the default articles.
        liveDataManager.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean finish) {
                if(finish) {
                    if(0 != liveDataManager.savedArticles.size() && null != liveDataManager.savedArticles.get(0).content) {
                        viewModel.setRecommendedArticles(liveDataManager.savedArticles.get(0).content.toString().substring(1, 6));
                    }else{
                        viewModel.setRecommendedArticles("US");
                    }
                }

            }
        });





        viewModel.getTopHeadlines().observe(
                getViewLifecycleOwner(),
                newsResponse -> {
                    if (newsResponse != null) {
                        articles = newsResponse.articles;
                        swipeAdapter.setArticles(articles);
                    }
                }
        );

        binding.homeLikeButton.setOnClickListener(v -> swipeCard(Direction.Right));
        binding.homeUnlikeButton.setOnClickListener(v -> swipeCard(Direction.Left));
    }

    private void swipeCard(Direction direction) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(direction)
                .setDuration(Duration.Normal.duration)
                .build();
        layoutManager.setSwipeAnimationSetting(setting);
        binding.homeCardStackView.swipe();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        final String TAG = "CardStackView";
        final String PREFIX_LIKE_MSG = "Liked ";
        final String PREFIX_UNLIKE_MSG = "Unliked ";
        if (direction == Direction.Left) {
            Log.d(TAG, PREFIX_UNLIKE_MSG + layoutManager.getTopPosition());
        } else if (direction == Direction.Right) {
            Log.d(TAG, PREFIX_LIKE_MSG + layoutManager.getTopPosition());
            Article article = articles.get(layoutManager.getTopPosition() - 1);
            viewModel.setFavoriteArticleInput(article);

            // push notification after saving the article
            pushNotification();
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    private void pushNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Successful Save Notification", "Successful Save Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        String message = "Successfully saved the article to local device. Enjoy reading :)";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "Successful Save Notification");
        builder.setContentTitle("Now you can read the article offline!");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.ic_thumb_up_24dp);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
        managerCompat.notify(1, builder.build());
    }
}