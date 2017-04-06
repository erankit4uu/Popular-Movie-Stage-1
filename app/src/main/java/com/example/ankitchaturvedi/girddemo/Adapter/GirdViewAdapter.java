package com.example.ankitchaturvedi.girddemo.Adapter;


import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.example.ankitchaturvedi.girddemo.ActivityMovieDetails;
import com.example.ankitchaturvedi.girddemo.R;
import com.example.ankitchaturvedi.girddemo.ResponseParser.MainGridResponseParser;
import com.example.ankitchaturvedi.girddemo.Utils.helper;
import com.squareup.picasso.Picasso;


/**
 * Created by Ankit.Chaturvedi on 01-Apr-17.
 */

public class GirdViewAdapter extends ArrayAdapter{
    private Context context;
    private LayoutInflater inflater;
    private MainGridResponseParser mMainGridResponseParser;

    private ImageView ivBasicImage;

    public GirdViewAdapter(@NonNull Context context,  MainGridResponseParser mMainGridResponseParser) {
        super(context, R.layout.grid_item,mMainGridResponseParser.results);

        this.context = context;
        this.mMainGridResponseParser = mMainGridResponseParser;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(null== convertView) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            ivBasicImage = (ImageView) convertView.findViewById(R.id.iv_grid);
        }


        Picasso.with(context)
                .load(helper.base_url+helper.poster_sizes+mMainGridResponseParser.results.get(position).poster_path)
                .into(ivBasicImage);

        final LayoutTransition transition = new LayoutTransition();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(convertView, "rotationY", 90f, 0f).
                setDuration(transition.getDuration(LayoutTransition.APPEARING));
        objectAnimator.start();
        transition.setAnimator(LayoutTransition.APPEARING, objectAnimator);
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING,40);
        parent.setLayoutTransition(transition);

        ivBasicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, ActivityMovieDetails.class);
                in.putExtra("poster_path",mMainGridResponseParser.results.get(position).poster_path);
                in.putExtra("overview",mMainGridResponseParser.results.get(position).overview);
                in.putExtra("release_date",mMainGridResponseParser.results.get(position).release_date);
                in.putExtra("vote_average",mMainGridResponseParser.results.get(position).vote_average);
                in.putExtra("adult",mMainGridResponseParser.results.get(position).adult);
                in.putExtra("title",mMainGridResponseParser.results.get(position).title);
                String transitionName = context.getString(R.string.title_name);
                ActivityOptions transitionActivityOptions = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, transitionName );
                }
                if (transitionActivityOptions != null) {
                    context.startActivity(in,transitionActivityOptions.toBundle());
                }else  context.startActivity(in);
            }
        });
        return convertView;
    }
}
