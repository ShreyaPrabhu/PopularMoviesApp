package com.example.shreyaprabhu.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    TextView title;
    TextView overview;
    TextView date;
    TextView ratings;
    ImageView poster_image;

    private android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        MovieAttributes movieClicked = (MovieAttributes) intent.getSerializableExtra(MovieRecyclerAdapter.MovieAtts);


        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
        date = (TextView) findViewById(R.id.releasedate);
        ratings = (TextView) findViewById(R.id.ratings);
        poster_image = (ImageView) findViewById(R.id.poster_image);


        if (movieClicked == null) {
            title.setText("null");
            overview.setText("null");
            date.setText("null");
            ratings.setText("null");
            poster_image.setImageResource(R.drawable.placeholder);

        } else {
            title.setText(movieClicked.getTitle());
            overview.setText(movieClicked.getoverview());
            date.setText("Movie Release date\n" + movieClicked.getreleaseDate());
            ratings.setText("Movie Rating\n" + movieClicked.getrating() + "/10");
            Picasso.with(this).load("http://image.tmdb.org/t/p/w500" + movieClicked.getposterPath()).placeholder(R.drawable.placeholder).into((poster_image));


        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }
}
