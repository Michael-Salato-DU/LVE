/* Band class with getters and setters

 */
package du.a188project1.bestdamapp;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Band extends RealmObject{
    @PrimaryKey
    private String name;
    private String genre;
    private String description;
    private int user_rating;
    private RealmList<Image> pictures;
    private RealmList<Review> reviews;

    // accessor method for name
    public String getName() {
        return name;
    }

    // mutator method for name
    public void setName(String name) {
        this.name = name;
    }

    // accessor method for genre
    public String getGenre() {
        return genre;
    }

    // mutator method for genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // accessor method for description
    public String getDescription() {
        return description;
    }

    // mutator method for description
    public void setDescription(String description) {
        this.description = description;
    }

    // accessor method for user_rating
    public int getUser_rating() {
        return user_rating;
    }

    // mutator method for user_rating
    public void setUser_rating(int user_rating) {
        this.user_rating = user_rating;
    }

    // access method for pictures
    public RealmList<Image> getPictures() {
        return pictures;
    }

    // mutator method for pictures
    public void setPictures(RealmList<Image> pictures) {
        this.pictures = pictures;
    }

    // accessor method for reviews
    public RealmList<Review> getReviews() {
        return reviews;
    }

    // mutator method for reviews
    public void setReviews(RealmList<Review> reviews) {
        this.reviews = reviews;
    }

}


