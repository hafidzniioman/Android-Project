package com.ibra.movie_catalog;


import org.json.JSONObject;

public class MovieModel  {

    private String title;
    private String description;
    private String releaseDate;
    private String poster;
    private String rate_count;
    private String rate;

    public MovieModel(JSONObject object){
        try{
            String titlee = object.getString("title");
            String desc = object.getString("description");
            String rdate = object.getString("releaseDate");
            String poster = object.getString("poster");
            String rating_count = object.getString("rate_count");
            String rating = object.getString("rate");

            this.title = titlee;
            this.description = desc;
            this.releaseDate = rdate;
            this.poster = poster;
            this.rate_count = rating_count;
            this.rate = rating;

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRate_count() {
        return rate_count;
    }

    public void setRate_count(String rate_count) {
        this.rate_count = rate_count;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
