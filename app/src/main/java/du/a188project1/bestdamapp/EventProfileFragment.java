/* Michael Salato
   "Event Profile" tab. Fragment that will show the information for an event.
   Can purchase tickets and follow the event from this tab.
 */

package du.a188project1.bestdamapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;


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
    private Realm realm;

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
        Snackbar followed_message = (Snackbar)Snackbar.make(
                view.findViewById(R.id.event_coordinator_layout), "Band followed!", LENGTH_SHORT);

        // Get a realm instance
        realm = Realm.getDefaultInstance();

        // Declare an EventActivity variable
        final EventActivity eventActivity = (EventActivity) this.getActivity();

        // Set the image of the band performing
        if(eventActivity.event.getPerformer().getPictures().size() != 0) {
            Bitmap bmp = BitmapFactory.decodeByteArray(eventActivity.event.getPerformer().getPictures().get(0).getImage(),
                    0, eventActivity.event.getPerformer().getPictures().get(0).getImage().length);
            imageView.setImageBitmap(bmp);
        }

        // Set the band name, venue, genre, and price range textViews.
        bandNameView.setText(eventActivity.event.getPerformer().getName());
        venueView.setText(eventActivity.event.getVenue().getVenueName());
        genreView.setText(eventActivity.event.getPerformer().getGenre());

        if ((eventActivity.event.getMinPrice() == 0) && (eventActivity.event.getMaxPrice() == 0)) {
            priceView.setText("SOLD OUT!");
        }
        else {
            // priceView displays as a range (i.e. $25 - $50 per ticket)
            priceView.setText("$"+Integer.toString(eventActivity.event.getMinPrice())+ " - $" +
                    Integer.toString(eventActivity.event.getMaxPrice()) + " per ticket");
        }

        // Link to the official website to buy the tickets.
        // source to link to a website: How to open a website when a Button is clicked in Android application?
        // https://stackoverflow.com/questions/5026349/how-to-open-a-website-when-a-button-is-clicked-in-android-application
        // user: Sampad
        // date: Oct. 31, 2016
        buyTicketsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(eventActivity.event.getTicketLink())); // set the website link
                startActivity(intent);
            } });

        //follow button adds event to user's saved events list
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                            // Get the RealmList of Events for this user
                            RealmList<Event> updatedList = eventActivity.user.getSaved_events();
                            // Add this event to the RealmList
                            updatedList.add(eventActivity.event);
                            // Set the user's updated RealmList of Events
                            eventActivity.user.setSaved_events(updatedList);
                            // Update in realm database
                            realm.copyToRealmOrUpdate(eventActivity.user);
                            // Notification snackbar indicating the band is followed
                            followed_message.show();
                    }
                });

            }
        });


        return view;
    }

}
