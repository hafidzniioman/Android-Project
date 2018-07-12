package com.ibra.movie_catalog;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieModel>>{
    ImageView temp;
    ListView listView;
    MovieAdapter adapter;
    EditText ed_search;
    Button btnSearch;

    static final String EXTRAS_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieModel model = (MovieModel)parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);

                intent.putExtra(MovieDetail.EXTRA_TITLE, model.getTitle());
                intent.putExtra(MovieDetail.EXTRA_RELEASE_DATE, model.getReleaseDate());
                intent.putExtra(MovieDetail.EXTRA_RATE, model.getRate());
                intent.putExtra(MovieDetail.EXTRA_RATE_COUNT, model.getRate_count());
                intent.putExtra(MovieDetail.EXTRA_OVERVIEW, model.getDescription());
                intent.putExtra(MovieDetail.EXTRA_POSTER, model.getPoster());

                startActivity(intent);
            }
        });

       ed_search = (EditText)findViewById(R.id.ed_search);
       btnSearch = (Button)findViewById(R.id.btnSearch);
       btnSearch.setOnClickListener(listener);
       temp = (ImageView)findViewById(R.id.temp);

       String title = ed_search.getText().toString();

       Bundle bundle = new Bundle();
       bundle.putString(EXTRAS_MOVIE,title);

       getLoaderManager().initLoader(0,bundle,this);


    }

    @Override
    public Loader<ArrayList<MovieModel>> onCreateLoader(int id, Bundle args){
        String titleMovie ="";
        if (args != null){
            titleMovie = args.getString(EXTRAS_MOVIE);
        }

        return  new MovieLoader(this,titleMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieModel>> loader, ArrayList<MovieModel> movieModels) {
        adapter.setData(movieModels);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieModel>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            String titleMovie = ed_search.getText().toString();

            if (TextUtils.isEmpty(titleMovie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, titleMovie);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };


}
