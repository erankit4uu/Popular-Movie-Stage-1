package com.example.ankitchaturvedi.girddemo;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;

import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ankitchaturvedi.girddemo.Utils.helper;
import com.squareup.picasso.Picasso;

/**
 * Created by Ankit.Chaturvedi on 04-Apr-17.
 */

public class ActivityMovieDetails extends AppCompatActivity {
    TextView mtv_movie_title,mtv_release_date,mtv_overview,mtv_censor_rating,mtv_rating;
    ImageView miv_collapsing,miv_back;
    RatingBar rating_bar;


    static String mposter_path;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setupWindowAnimations();

        mtv_movie_title = (TextView) findViewById(R.id.tv_movie_title);
        miv_collapsing = (ImageView) findViewById(R.id.iv_collapsing);
        miv_back = (ImageView) findViewById(R.id.iv_back);
        mtv_overview = (TextView) findViewById(R.id.tv_overview);
        mtv_release_date = (TextView) findViewById(R.id.tv_release_date);
        mtv_censor_rating = (TextView) findViewById(R.id.tv_censor_rating);
        mtv_rating = (TextView) findViewById(R.id.tv_rating);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);

        miv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        Intent intent = getIntent();
        mtv_movie_title.setText(String.format("Title: %s", intent.getStringExtra("title")));
        mposter_path=intent.getStringExtra("poster_path");
        mtv_overview.setText(String.format("Overview: %s", intent.getStringExtra("overview")));
        mtv_release_date.setText(String.format("Release Date: %s", intent.getStringExtra("release_date")));
        rating_bar.setRating(intent.getFloatExtra("vote_average",0));
        rating_bar.setClickable(false);
        mtv_rating.setText(String.format("%s/10", rating_bar.getRating()));
        if(intent.getBooleanExtra("adult",false)){
            mtv_censor_rating.setVisibility(View.VISIBLE);
        }else mtv_censor_rating.setVisibility(View.GONE);


        Picasso.with(ActivityMovieDetails.this)
                .load(helper.base_url+helper.poster_original+mposter_path)
                .into(miv_collapsing);

//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+helper.base_url+helper.poster_sizes+mposter_path);
    }
    private void setupWindowAnimations() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.slide);
            getWindow().setExitTransition(slide);

            getWindow().setEnterTransition(slide);
        }
    }
}
