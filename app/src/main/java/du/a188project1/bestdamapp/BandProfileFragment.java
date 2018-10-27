/* Michael Salato
   Fragment that will show information about the band in the selected event.
 */

package du.a188project1.bestdamapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class BandProfileFragment extends Fragment {

    // Declare variables
    private ImageView imageView;
    private TextView bandNameView;
    private TextView userRatingView;
    private TextView bandDescriptionView;

    public BandProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_band_profile, container, false);

        // Tie variables to the respective views
        imageView = (ImageView) view.findViewById(R.id.band_images_view);
        bandNameView = (TextView) view.findViewById(R.id.band_name_view);
        userRatingView = (TextView) view.findViewById(R.id.user_rating_view);
        bandDescriptionView = (TextView) view.findViewById(R.id.band_description_view);

        final EventActivity eventActivity = (EventActivity) this.getActivity();

        // set the image
        if(eventActivity.event.getPerformer().getPictures().size() != 0) {
            Bitmap bmp = BitmapFactory.decodeByteArray(eventActivity.event.getPerformer().getPictures().get(1).getImage(),
                    0, eventActivity.event.getPerformer().getPictures().get(1).getImage().length);
            imageView.setImageBitmap(bmp);
        }

        // Set the band name and band description textViews.
        bandNameView.setText(eventActivity.event.getPerformer().getName());
        bandDescriptionView.setText(eventActivity.event.getPerformer().getDescription());

        // get the user rating for this performer
        int userRating = eventActivity.event.getPerformer().getUser_rating();
        // create an empty string
        String userRatingString = "";
        // loop (zero to userRating) times and concatenate a new * each loop
        for (int i = 0; i < userRating; i++) {
            userRatingString += "* ";
        }

        // set the user rating textView
        userRatingView.setText(userRatingString);

        return view;
    }

}
