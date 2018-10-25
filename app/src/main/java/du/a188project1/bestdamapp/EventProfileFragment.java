/* Michael Salato
   Fragment that will show the information for an event.
 */

package du.a188project1.bestdamapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventProfileFragment extends Fragment {

    // Declare variables
    private ImageView imageView;
    private TextView bandNameView;
    private TextView venueView;
    private TextView genreView;
    private TextView priceView;
    private Button followButton;
    private Button buyTicketsButton;

    public EventProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_profile, container, false);

        // Tie variables to the respective views
        imageView = (ImageView) view.findViewById(R.id.venue_images_view);
        bandNameView = (TextView) view.findViewById(R.id.band_name_view);
        venueView = (TextView) view.findViewById(R.id.venue_view);
        genreView = (TextView) view.findViewById(R.id.genre_view);
        priceView = (TextView) view.findViewById(R.id.price_view);
        followButton = (Button) view.findViewById(R.id.follow_button);
        buyTicketsButton = (Button) view.findViewById(R.id.buy_tickets_button);

        final EventActivity eventActivity = (EventActivity) this.getActivity();

        // Set the band name, venue, genre, and price range textViews.
        bandNameView.setText(eventActivity.event.getPerformer().getName());
        venueView.setText(eventActivity.event.getVenue().getVenueName());
        genreView.setText(eventActivity.event.getPerformer().getGenre());
        priceView.setText(Integer.toString(eventActivity.event.getMinPrice()));

        // Link to the official website to buy the tickets.
        // source to link to a website:
        // https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
        // user: Sampad
        buyTicketsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(eventActivity.event.getTicketLink()));
                startActivity(intent);
            } });

        //follow button adds event to user's saved events list
//        followButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        return view;
    }

}
