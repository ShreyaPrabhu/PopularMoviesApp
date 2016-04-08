package com.example.shreyaprabhu.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Shreya Prabhu on 4/8/2016.
 */
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieItemViewHolder> {



    private List<MovieAttributes> moviesList;
    private static Context mContext;
    private MovieAttributes mMovies;


    public static class MovieItemViewHolder extends RecyclerView.ViewHolder {

        public MovieItemViewHolder(View itemView) {
            super(itemView);

        }
    }
    public MovieRecyclerAdapter(Context context, List<MovieAttributes> movies) {
        super();
        this.mContext = context;
        this.moviesList = movies;

    }


    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }



}
