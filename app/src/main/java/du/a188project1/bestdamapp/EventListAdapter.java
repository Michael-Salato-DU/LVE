/*
Adapter for lists of events in MainActivity fragments
 */
package du.a188project1.bestdamapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import io.realm.RealmList;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.SuggestionViewHolder>{

    //declare variables
    private Context context;
    private RealmList<Event> events;
    private RecyclerViewClickListener mListener;

    //EventListAdapter constructor
    public EventListAdapter(Context context, RealmList<Event> dataSet, RecyclerViewClickListener clickListener) {
        this.context = context;
        this.events = dataSet;
        this.mListener = clickListener;
    }

    //static class for a view holder
    public static class SuggestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //declare variables
        public TextView performerView;
        public TextView venueView;
        public TextView dateView;
        private RecyclerViewClickListener mListener;

        //link the variables to the views in the layout
        public SuggestionViewHolder(View v, RecyclerViewClickListener listener){
            super(v);
            performerView = v.findViewById(R.id.performer_view);
            venueView = v.findViewById(R.id.venue_view);
            dateView = v.findViewById(R.id.date_view);
            mListener = listener;
            v.setOnClickListener(this);

        }

        //when a list item is clicked, perform the click listener's onClick function
        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    //function to return the number of items in the list
    @Override
    public int getItemCount() {
        return events.size();
    }

    //function to return a suggestion view holder
    @Override
    public EventListAdapter.SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_cell, parent, false);
        SuggestionViewHolder vh = new SuggestionViewHolder(v, mListener);
        return vh;
    }

    //function to set the values of the text holders
    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position){
        holder.performerView.setText(events.get(position).getPerformer().getName());
        holder.dateView.setText(events.get(position).getDate());
        holder.venueView.setText(events.get(position).getVenue().getVenueName());
    }
}
