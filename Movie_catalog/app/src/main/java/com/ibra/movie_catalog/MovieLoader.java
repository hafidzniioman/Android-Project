package com.ibra.movie_catalog;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieLoader extends AsyncTaskLoader<ArrayList<MovieModel>> {

    private ArrayList<MovieModel> mData;
    private boolean hasil =  false;
    private String mTitleMovie;

    private static final String API_KEY="d9f7bcde31d4d4036fd805ca62ce29d0";

    public MovieLoader(final Context context, String titleMovie){
        super(context);

        onContentChanged();
        this.mTitleMovie = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasil)
            deliverResult(mData);
    }


    @Override
    public void deliverResult(ArrayList<MovieModel> data) {
        mData =  data;
        hasil = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasil){
            onReleaseResource(mData);
            mData = null;
            hasil = false;
            }
    }


    private void onReleaseResource(ArrayList<MovieModel> mData){

    }

    @Override
    public ArrayList<MovieModel> loadInBackground() {
        SyncHttpClient client =  new SyncHttpClient();

        final ArrayList<MovieModel> movie_Model = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY +
                "&language=en-US&query=" + mTitleMovie;
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result =  new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("result");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject film = list.getJSONObject(i);
                        MovieModel movieModels = new MovieModel(film);
                        movie_Model.add(movieModels);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movie_Model;
    }
}
