/* Michael Salato
   Adapter that takes in a Band object from ReviewFragment.java. Sets the
   review message and review image for that Band for review_cell.xml.
 */

package du.a188project1.bestdamapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    // Declare variables
    private Context context;
    private Band bandReviews; // holds the Band object passed in from ReviewFragment.java

    // Constructor for ReviewAdapter. Sets the context and Band.
    public ReviewAdapter(Context context, Band dataSet) {
        this.context = context;
        this.bandReviews = dataSet;
    }

    // ReviewViewHolder - Class that ties view variables to views in xml document
    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        // Declare view variables
        public TextView bandReviewView;
        public ImageView bandImageView;

        // ReviewViewHolder ties the view variables to the respective views in review_cell.xml
        public ReviewViewHolder(View v) {
            super(v);
            bandReviewView = v.findViewById(R.id.band_review_view);
            bandImageView = v.findViewById(R.id.band_image_view);
        }
    }

    // getItemCount() returns the number of Reviews for this band
    @Override
    public int getItemCount() {
        return bandReviews.getReviews().size();
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_cell, parent, false);
        // Call ReviewViewHolder function
        ReviewViewHolder vh = new ReviewViewHolder(v);
        return vh;
    }

    // onBindViewHolder() sets the review message and review image
    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        // set review message
        holder.bandReviewView.setText(bandReviews.getReviews().get(position).getMessage());

        // set review image
        if (bandReviews.getReviews().get(position).getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bandReviews.getReviews().get(position).getImage(),
                    0, bandReviews.getReviews().get(position).getImage().length);
            holder.bandImageView.setImageBitmap(bmp);
        }
    }

}
