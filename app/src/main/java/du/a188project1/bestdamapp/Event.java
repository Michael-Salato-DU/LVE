/*  Michael Salato
    Event class with getters and setters
 */

package du.a188project1.bestdamapp;

import java.io.Serializable;


public class Event implements Serializable {
    private String date;
    private Band performer;
    private Venue venue;
    private int priceRange;
    private String ticketLink;
    private byte[][] images;

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

    // accessor method for priceRange
    public int getPriceRange() {
        return priceRange;
    }

    // mutator method for priceRange
    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }


    // accessor method for ticketLink
    public String getTicketLink() {
        return ticketLink;
    }

    // mutator method for ticketLink
    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    // accessor method for images
    public byte[][] getImages() {
        return images;
    }

    // mutator method for images
    public void setImages(byte[][] images) {
        this.images = images;
    }
}
