/* Michael Salato
   Fragment that will show reviews for a band.
 */

package du.a188project1.bestdamapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

    private RecyclerView reviewList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter reviewAdapter;
    private TextView bandNameView;
    private Button writeReviewButton;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_review, container, false);

        // Tie variables to the respective views
        bandNameView = (TextView) view.findViewById(R.id.band_name_view);
        writeReviewButton = (Button) view.findViewById(R.id.write_review_button);

        final EventActivity eventActivity = (EventActivity) this.getActivity();

        // Set the band name
        bandNameView.setText(eventActivity.event.getPerformer().getName());

        reviewList = (RecyclerView)view.findViewById(R.id.review_list);

        layoutManager = new LinearLayoutManager(getContext());
        reviewList.setLayoutManager(layoutManager);

        reviewAdapter = new ReviewAdapter(getContext(), eventActivity.event.getPerformer());
        reviewList.setAdapter(reviewAdapter);

        return view;
    }

}
