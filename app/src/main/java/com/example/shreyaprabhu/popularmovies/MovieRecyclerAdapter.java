package com.example.shreyaprabhu.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shreya Prabhu on 4/8/2016.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieItemViewHolder> {



    private List<MovieAttributes> moviesList;
    private static Context mContext;

    public static String MovieAtts = "MovieAtts";

    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private MovieAttributes mMovie;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            //set onitemClickListener for each movie_item

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra(MovieAtts, mMovie);
                    mContext.startActivity(intent);
                }
            });
        }

        //This has to be implemented when an item in recyclerview is clicked.
        public void RetrieveImageDetails(MovieAttributes movie, MovieItemViewHolder holder) {
            mMovie = movie;

            String posterUrl = mMovie.getposterPath();
            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500" + posterUrl).placeholder(R.drawable.placeholder).into((holder).mImageView);
        }

    }

    public MovieRecyclerAdapter(Context context, List<MovieAttributes> movies) {
        super();
        this.mContext = context;
        this.moviesList = movies;

    }


    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {

        MovieAttributes Movie = moviesList.get(position);
        holder.RetrieveImageDetails(Movie, holder);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }



}
