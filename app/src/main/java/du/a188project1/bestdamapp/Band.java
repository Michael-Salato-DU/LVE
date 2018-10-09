package du.a188project1.bestdamapp;

import java.io.Serializable;

public class Band implements Serializable{
    private String name;
    private String genre;
    private String description;
    private int user_rating;
    private byte[][] pictures;
    private String[] reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    public byte[][] getPictures() {
        return pictures;
    }

    public void setPictures(byte[][] pictures) {
        this.pictures = pictures;
    }

    public String[] getReviews() {
        return reviews;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }
}


