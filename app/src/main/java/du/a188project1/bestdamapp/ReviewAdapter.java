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

    private Context context;
    private Band bandReviews;

    public ReviewAdapter(Context context, Band dataSet) {
        this.context = context;
        this.bandReviews = dataSet;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        public TextView bandReviewView;
        public ImageView bandImageView;

        public ReviewViewHolder(View v) {
            super(v);
            bandReviewView = v.findViewById(R.id.band_review_view);
            bandImageView = v.findViewById(R.id.band_image_view);
        }
    }

    @Override
    public int getItemCount() {
        return bandReviews.getReviews().size();
    }

    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_cell, parent, false);
        ReviewViewHolder vh = new ReviewViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bandReviewView.setText(bandReviews.getReviews().get(position).getMessage());

        if (bandReviews.getReviews().get(position).getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bandReviews.getReviews().get(position).getImage(),
                    0, bandReviews.getReviews().get(position).getImage().length);
            holder.bandImageView.setImageBitmap(bmp);
        }
    }

}
