/* Michael Salato
   EventActivity class - ___
 */

package du.a188project1.bestdamapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class EventActivity extends AppCompatActivity {

    // Declare class variables
    // find better names for static xml variables
    private ImageView imageView; // change name
    private TextView bandNameView;
    private TextView venueView;
    private TextView genreView;
    private TextView priceView;
    private TextView bandDescView;
    private Button followButton;
    private Button buyTicketsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Tie variables to the respective views
        imageView = (ImageView) findViewById(R.id.image_view); // change name
        bandNameView = (TextView) findViewById(R.id.band_name_view);
        venueView = (TextView) findViewById(R.id.venue_view);
        genreView = (TextView) findViewById(R.id.genre_view);
        priceView = (TextView) findViewById(R.id.price_view);
        bandDescView = (TextView) findViewById(R.id.band_desc_view);
        followButton = (Button) findViewById(R.id.follow_button);
        buyTicketsButton = (Button) findViewById(R.id.buy_tickets_button);

        // Get the Event object passed as an intent extra from MainActivity.
        final Event event = (Event) getIntent().getSerializableExtra("event");

        // Set the band name, venue, genre, price range, and band description textViews.
        bandNameView.setText(event.getPerformer().getName());
        venueView.setText(event.getVenue().getVenueName());
        genreView.setText(event.getPerformer().getGenre());
//        priceView.setText(Integer.toString(event.getPriceRange()));
        bandDescView.setText(event.getPerformer().getDescription());

        // Link to the official website to buy the tickets.
        // source to link to a website:
        // https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
        // user: Sampad
        buyTicketsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(event.getTicketLink()));
                startActivity(intent);
            } });
    }
}
