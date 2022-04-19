package edu.neu.madcourse.numad22sp_team36_tinnews.ui.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad22sp_team36_tinnews.R;
import edu.neu.madcourse.numad22sp_team36_tinnews.databinding.SwipeNewsCardBinding;
import edu.neu.madcourse.numad22sp_team36_tinnews.model.Article;

public class CardSwipeAdapter extends RecyclerView.Adapter<CardSwipeAdapter.CardSwipeViewHolder> {

    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
    }

    @NonNull
    @Override
    public CardSwipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_news_card, parent, false);
        return new CardSwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardSwipeViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.titleTextView.setText(article.title);
        holder.descriptionTextView.setText(article.description);
        if (!TextUtils.isEmpty(article.urlToImage)) {
            Picasso.get().load(article.urlToImage).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class CardSwipeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public CardSwipeViewHolder(@NonNull View itemView) {
            super(itemView);

            SwipeNewsCardBinding binding = SwipeNewsCardBinding.bind(itemView);
            imageView = binding.swipeCardImageView;
            titleTextView = binding.swipeCardTitle;
            descriptionTextView = binding.swipeCardDescription;
        }
    }
}
