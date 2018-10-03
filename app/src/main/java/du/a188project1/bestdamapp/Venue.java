// Tess Julien
// October 2, 2018
//Venue class with getters and setters

package du.a188project1.bestdamapp;

public class Venue {
    private String venueName;
    private String address;
    private Float avgUserRating;
    private int venueType;
    private boolean older21;

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getAvgUserRating() {
        return avgUserRating;
    }

    public void setAvgUserRating(Float avgUserRating) {
        this.avgUserRating = avgUserRating;
    }

    //0 = restaurant only
    //1 = bar only
    //2 = restaurant and bar
    public int getVenueType() {
        return venueType;
    }

    public void setVenueType(int venueType) {
        this.venueType = venueType;
    }

    //if venue only accepts patrons 21 years old or older, set older21 to true
    public boolean isOlder21() {
        return older21;
    }

    public void setOlder21(boolean older21) {
        this.older21 = older21;
    }
}
