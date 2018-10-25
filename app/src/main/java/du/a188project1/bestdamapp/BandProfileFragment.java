/* Michael Salato
   Fragment that will show information about the band in the selected event.
 */

package du.a188project1.bestdamapp;


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

        // Set the band name, user rating, and band description textViews.
        bandNameView.setText(eventActivity.event.getPerformer().getName());
        userRatingView.setText(Integer.toString(eventActivity.event.getPerformer().getUser_rating()));
        bandDescriptionView.setText(eventActivity.event.getPerformer().getDescription());

        return view;
    }

}
