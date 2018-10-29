/* Michael Salato
   "Reviews" tab. Fragment that will show user reviews for a band.
   Also has a button to create a review.
 */

package du.a188project1.bestdamapp;


import android.content.Intent;
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

import io.realm.Realm;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

    // Declare variables
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

        // Declare an EventActivity variable
        final EventActivity eventActivity = (EventActivity) this.getActivity();

        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = (Event) eventActivity.event;
                Intent intent = new Intent(view.getContext(), ReviewActivity.class);
                intent.putExtra("band", event.getPerformer().getName());
                intent.putExtra("user email", eventActivity.user.getEmail());
                startActivity(intent);
            }
        });

        // Set the band name
        bandNameView.setText(eventActivity.event.getPerformer().getName());

        // Tie RecyclerView variable to the RecyclerView
        reviewList = (RecyclerView)view.findViewById(R.id.review_list);

        // set LayoutMangager
        layoutManager = new LinearLayoutManager(getContext());
        reviewList.setLayoutManager(layoutManager);

        // Create a ReviewAdapter and pass in the band
        reviewAdapter = new ReviewAdapter(getContext(), eventActivity.event.getPerformer());
        reviewList.setAdapter(reviewAdapter);
        refreshList();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshList();
    }

    private void refreshList(){
        Realm realm = Realm.getDefaultInstance();
        EventActivity eventActivity = (EventActivity) this.getActivity();
        final RealmList<Review> reviews = eventActivity.event.getPerformer().getReviews();

        writeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = (Event) eventActivity.event;
                Intent intent = new Intent(view.getContext(), ReviewActivity.class);
                intent.putExtra("band", event.getPerformer().getName());
                intent.putExtra("user email", eventActivity.user.getEmail());
                startActivity(intent);
            }
        });

        reviewAdapter = new ReviewAdapter(getContext(), eventActivity.event.getPerformer());
        reviewList.setAdapter(reviewAdapter);

    }

}
