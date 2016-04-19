package com.example.shreyaprabhu.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Fragment activity has Movielist

public class MovieMainFragment extends Fragment {


    private android.support.v7.widget.Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private List<MovieAttributes> mMovies = new ArrayList<>();
    public String BaseUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        fetchMovieDetails fetchMovieDetails = new fetchMovieDetails();
        fetchMovieDetails.execute();
        BaseUrl = "http://api.themoviedb.org/3/movie/now_playing";

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_movie_main_fragment, container, false);

        //add custom toolbar
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_movie_main_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        if (isAdded()) {
            mRecyclerView.setAdapter(new MovieRecyclerAdapter(getActivity(), mMovies));
        }


        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_movie_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_most_popular) {

            sortMovies("popular");
            return true;
        }

        if (id == R.id.action_highest_rated) {

            sortMovies("rate");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void sortMovies(String a){
        if(a.equals("popular")) {
            BaseUrl = "http://api.themoviedb.org/3/movie/popular";
            fetchMovieDetails fetchMovieDetails = new fetchMovieDetails();
            fetchMovieDetails.execute();

        }
        if(a.equals("rate")) {
            BaseUrl = "http://api.themoviedb.org/3/movie/top_rated";
            fetchMovieDetails fetchMovieDetails = new fetchMovieDetails();
            fetchMovieDetails.execute();

        }
    }

    private class fetchMovieDetails extends AsyncTask<Void, Void, List<MovieAttributes>> {


        private final String TAG = fetchMovieDetails.class.getSimpleName();
        private final String KEY ="";  //here goes the api key


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<MovieAttributes> movies) {
            super.onPostExecute(movies);
            mMovies = movies;

            if (isAdded()) {
                mRecyclerView.setAdapter(new MovieRecyclerAdapter(getActivity(), mMovies));
            }

        }

        @Override
        protected List<MovieAttributes> doInBackground(Void... params) {


            List<MovieAttributes> movies = new ArrayList<>();

            try {
                OkHttpClient client = new OkHttpClient();

                // Build URL
                String url = Uri.parse(BaseUrl)
                        .buildUpon()
                        .appendQueryParameter("api_key", KEY)
                        .appendQueryParameter("page", "1")
                        .build().toString();


                // Fetch Data from URL
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Call call = client.newCall(request);
                Response response = call.execute();
                if (!response.isSuccessful()) {
                    throw new IOException("Err Code: " + response);
                }
                String responseStr = response.body().string();
                Log.v(TAG, "Response Str: " + responseStr);
                movies = fetchMovieData(responseStr);

            } catch (IOException e) {
                Log.e(TAG, "Failed to Fetch Data: ", e);
            }
            return movies;
        }


    }


    public static List<MovieAttributes> fetchMovieData(String jsonStr) {


        List<MovieAttributes> movies = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("results");


            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject movieJson = jsonArray.getJSONObject(i);
                MovieAttributes movie = new MovieAttributes();
                movie.setTitle(movieJson.getString("title"));
                movie.setposterPath(movieJson.getString("poster_path"));
                movie.setoverview(movieJson.getString("overview"));
                movie.setreleaseDate(movieJson.getString("release_date"));
                movie.setrating(movieJson.getString("vote_average"));
                movies.add(movie);
            }
        } catch (JSONException e) {
            Log.e("JsonException", e.getMessage(), e);
            e.printStackTrace();

        }
        return movies;
    }

}
