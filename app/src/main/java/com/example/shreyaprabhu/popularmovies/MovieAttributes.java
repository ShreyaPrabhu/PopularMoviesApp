package com.example.shreyaprabhu.popularmovies;

import java.io.Serializable;

/**
 * Created by Shreya Prabhu on 4/8/2016.
 */
public class MovieAttributes implements Serializable {


    private String Title;
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String rating;

    public MovieAttributes(String Title, String posterPath, String overview, String rating, String releaseDate) {
        this.Title = Title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public MovieAttributes(){

        //Constructer
    }

    //Methods to get and set each attributes

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getposterPath() {
        return posterPath;
    }

    public void setposterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getoverview() {
        return overview;
    }

    public void setoverview(String overview) {
        this.overview = overview;
    }

    public String getreleaseDate() {
        return releaseDate;
    }

    public void setreleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getrating() {
        return rating;
    }

    public void setrating(String rating) {
        this.rating = rating;
    }
}

