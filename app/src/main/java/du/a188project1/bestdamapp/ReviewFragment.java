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

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_review, container, false);

        bandNameView = (TextView) view.findViewById(R.id.band_name_view);
        bandNameView.setText("Justin Bieber and Friends");

        Band band = new Band();
        RealmList<Review> bandReviews = new RealmList<Review>();
        reviewList = (RecyclerView)view.findViewById(R.id.review_list);

        Review review1 = new Review();
        review1.setMessage("Amazing!");

        Review review2 = new Review();
        review2.setMessage("So energetic!");

        Review review3 = new Review();
        review3.setMessage("Meh.");

        Review review4 = new Review();
        review4.setMessage("I liked it.");

        Review review5 = new Review();
        review5.setMessage("It was aight.");

        bandReviews.add(review1);
        bandReviews.add(review2);
        bandReviews.add(review3);
        bandReviews.add(review4);
        bandReviews.add(review5);

        //TODO:ERROR
        band.setReviews(bandReviews);

        layoutManager = new LinearLayoutManager(getContext());
        reviewList.setLayoutManager(layoutManager);

        reviewAdapter = new ReviewAdapter(getContext(), band);
        reviewList.setAdapter(reviewAdapter);

        return view;
    }

}
