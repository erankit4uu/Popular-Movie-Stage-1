package com.example.ankitchaturvedi.girddemo.objects;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Ankit.Chaturvedi on 01-Apr-17.
 */

public class GetImageResults {

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("adult")
    public Boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("original_title")
    public String original_title;

    @SerializedName("title")
    public String title;

    @SerializedName("popularity")
    public Double popularity;

    @SerializedName("vote_average")
    public Float vote_average;


}
