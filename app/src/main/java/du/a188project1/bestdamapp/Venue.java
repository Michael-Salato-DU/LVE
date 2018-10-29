// Tess Julien
//Venue class with getters and setters

package du.a188project1.bestdamapp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Venue extends RealmObject{
    @PrimaryKey
    private String venueName;
    private String address;
    private int venueType;
    private boolean older21;

    // accessor method for venueName
    public String getVenueName() {
        return venueName;
    }

    // mutator method for venueName
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    // accessor method for address
    public String getAddress() {
        return address;
    }

    // mutator method for address
    public void setAddress(String address) {
        this.address = address;
    }

    // accessor method for venueType
    //0 = restaurant only
    //1 = bar only
    //2 = restaurant and bar
    public int getVenueType() {
        return venueType;
    }

    // mutator method for venueType
    public void setVenueType(int venueType) {
        this.venueType = venueType;
    }

    // accessor method for older21
    //if venue only accepts patrons 21 years old or older, set older21 to true
    public boolean isOlder21() {
        return older21;
    }

    // mutator method for older21
    public void setOlder21(boolean older21) {
        this.older21 = older21;
    }
}
