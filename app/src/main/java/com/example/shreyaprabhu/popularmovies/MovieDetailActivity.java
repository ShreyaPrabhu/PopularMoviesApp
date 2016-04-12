package com.example.shreyaprabhu.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    TextView title;
    TextView overview;
    TextView date;
    TextView ratings;

    private android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        MovieAttributes movieClicked = (MovieAttributes) intent.getSerializableExtra(MovieRecyclerAdapter.MovieAtts);


        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
        date = (TextView) findViewById(R.id.releasedate);
        ratings = (TextView) findViewById(R.id.ratings);


        if (movieClicked == null) {
            title.setText("null");
            overview.setText("null");
            date.setText("null");
            ratings.setText("null");

        } else {
            title.setText(movieClicked.getTitle());
            overview.setText(movieClicked.getoverview());
            date.setText(movieClicked.getreleaseDate());
            ratings.setText(movieClicked.getrating());


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

        return super.onOptionsItemSelected(item);
    }
}
