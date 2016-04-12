package com.example.shreyaprabhu.popularmovies;

import java.io.Serializable;

/**
 * Created by Shreya Prabhu on 4/8/2016.
 */
public class MovieAttributes implements Serializable {


    private String title;
    private String poster_path;
    private String overview;
    private String release_date;
    private String vote_average;

    public MovieAttributes(String title, String poster_path, String overview, String vote_average, String release_date) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public MovieAttributes(){

        //Constructer
    }

    //Methods to get and set each attributes

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getposterPath() {
        return poster_path;
    }

    public void setposterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getoverview() {
        return overview;
    }

    public void setoverview(String overview) {
        this.overview = overview;
    }

    public String getreleaseDate() {
        return release_date;
    }

    public void setreleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getrating() {
        return vote_average;
    }

    public void setrating(String vote_average) {
        this.vote_average = vote_average;
    }
}

