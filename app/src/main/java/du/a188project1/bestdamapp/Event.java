/*  Michael Salato
    Event class with getters and setters
 */

package du.a188project1.bestdamapp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Event extends RealmObject {
    @PrimaryKey
    private String id;
    private String date;
    private Band performer;
    private Venue venue;
    private int minPrice;
    private int maxPrice;
    private String ticketLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // accessor method for date
    public String getDate() {
        return date;
    }

    // mutator method for date
    public void setDate(String date) {
        this.date = date;
    }

    // accessor method for performer
    public Band getPerformer() {
        return performer;
    }

    // mutator method for performer
    public void setPerformer(Band performer) {
        this.performer = performer;
    }

    // accessor method for venue
    public Venue getVenue() {
        return venue;
    }

    // mutator method for venue
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    // accessor method for minPrice
    public int getMinPrice() {
        return minPrice;
    }

    // mutator method for minPrice
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    // accessor method for maxPrice
    public int getMaxPrice() {
        return maxPrice;
    }

    // mutator method for maxPrice
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    // accessor method for ticketLink
    public String getTicketLink() {
        return ticketLink;
    }

    // mutator method for ticketLink
    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }
}
