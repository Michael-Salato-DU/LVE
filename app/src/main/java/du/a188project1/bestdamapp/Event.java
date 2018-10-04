package du.a188project1.bestdamapp;

import java.time.LocalDate;

public class Event {
    private LocalDate date;
    private Band performer;
    private String venue;
    private int priceRange;
    private String genre;
    private String ticketLink;
    private byte[] image;

    // accessor method for date
    public LocalDate getDate() {
        return date;
    }

    // mutator method for date
    public void setDate(LocalDate date) {
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
    public String getVenue() {
        return venue;
    }

    // mutator method for venue
    public void setVenue(String venue) {
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

    // accessor method for genre
    public String getGenre() {
        return genre;
    }

    // mutator method for genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // accessor method for ticketLink
    public String getTicketLink() {
        return ticketLink;
    }

    // mutator method for ticketLink
    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
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
