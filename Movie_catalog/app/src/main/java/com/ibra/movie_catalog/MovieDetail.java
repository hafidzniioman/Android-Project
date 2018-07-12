package com.ibra.movie_catalog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetail extends AppCompatActivity {

    public static  String EXTRA_TITLE           = "extra_title";
    public static  String EXTRA_OVERVIEW        = "extra_overview";
    public static  String EXTRA_RELEASE_DATE    = "extra_release_date";
    public static  String EXTRA_POSTER          = "extra_poster";
  //  public static  String EXTRA_GENRE           = "extra_genre";
    public static  String EXTRA_RATE_COUNT      = "extra_rate_count";
    public static  String EXTRA_RATE            = "extra_rate";

    private TextView tvTitle,tvReleaseDate,tvRating,tvOverview;
    private ImageView tempPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitle     = (TextView)findViewById(R.id.tv_movie_title);
        tvReleaseDate = (TextView)findViewById(R.id.tv_release_date);
        tvRating =(TextView)findViewById(R.id.tv_rating);
    //    tvMovieGenre= (TextView)findViewById(R.id.tv_genre);
        tvOverview =(TextView)findViewById(R.id.tv_over);
        tempPoster = (ImageView)findViewById(R.id.temp);

        String title       = getIntent().getStringExtra(EXTRA_TITLE);
        String releaseDate = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        String rating      = getIntent().getStringExtra(EXTRA_RATE);
        String rating_count= getIntent().getStringExtra(EXTRA_RATE_COUNT);
    //    String movieGenre  = getIntent().getStringExtra(EXTRA_GENRE);
        String overview    = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String temp        = getIntent().getStringExtra(EXTRA_POSTER);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = date_format.parse(releaseDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE,dd MM, yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);
        }catch (ParseException e){
            e.printStackTrace();
        }

        tvTitle.setText(title);
        tvOverview.setText(overview);
        tvRating.setText(rating_count + "Ratings("+ rating +"/10)");
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500/" + temp).into(tempPoster);
    }
}
