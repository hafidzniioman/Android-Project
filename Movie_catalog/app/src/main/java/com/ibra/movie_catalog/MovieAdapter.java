package com.ibra.movie_catalog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieModel> data = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
    private String final_overview;

    public MovieAdapter(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieModel> model){
        data = model;
        notifyDataSetChanged();
    }

    public void addItem(final MovieModel model){
        data.add(model);
        notifyDataSetChanged();
    }

    public void clearData(){
        data.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item_movie, null);
            holder.temp = (ImageView)convertView.findViewById(R.id.temp);
            holder.tv_movie_title = (TextView)convertView.findViewById(R.id.tv_movie_title);
            holder.tv_title_original = (TextView)convertView.findViewById(R.id.tv_title_original);
            holder.tv_rating = (TextView)convertView.findViewById(R.id.tv_rating);
            holder.tv_release_year = (TextView)convertView.findViewById(R.id.tv_release_year);
            convertView.setTag(holder);
        }

        else
            {
                holder = (ViewHolder)convertView.getTag();
        }

                holder.tv_movie_title.setText(data.get(position).getTitle());
                String overview = data.get(position).getDescription();
                if (TextUtils.isEmpty(overview)){
                    final_overview = "Tidak ada Data";
                }else{
                    final_overview = overview;
                }
                holder.tv_title_original.setText(final_overview);

                String retrieveDate = data.get(position).getReleaseDate();
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(retrieveDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MM dd, yyyy");
            String release_date = new_date_format.format(date);
            holder.tv_release_year.setText(release_date);
        }catch (ParseException e){
            e.printStackTrace();
        }

        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/"+ data.get(position).getPoster())
                .placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_background))
                .error(context.getResources().getDrawable(R.drawable.poster))
                .into(holder.temp);

        return convertView;
    }



    public static class ViewHolder{
        ImageView temp;
        TextView tv_movie_title,tv_title_original,tv_rating,tv_release_year;
    }
}
