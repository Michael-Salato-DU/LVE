/* Review class with getters and setters

 */

package du.a188project1.bestdamapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject{
    @PrimaryKey
    private String id;
    private User user;
    private String message;
    private float rating;
    private byte[] image;

    // accessor method for id
    public String getId() {
        return id;
    }

    // mutator method for id
    public void setId(String id) {
        this.id = id;
    }

    // accessor method for user
    public User getUser() {
        return user;
    }

    // mutator method for user
    public void setUser(User user) {
        this.user = user;
    }

    // accessor method for message
    public String getMessage() {
        return message;
    }

    // mutator method for message
    public void setMessage(String message) {
        this.message = message;
    }

    // accessor method for rating
    public float getRating() {
        return rating;
    }

    // mutator method for rating
    public void setRating(float rating) {
        this.rating = rating;
    }

    // accessor method for image
    public byte[] getImage() {
        return image;
    }

    // mutator method for image
    public void setImage(byte[] image) {
        this.image = image;
    }
}
