package du.a188project1.bestdamapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.SuggestionViewHolder>{

    private Context context;
    private RealmList<Event> events;
    private RecyclerViewClickListener mListener;

    public EventListAdapter(Context context, RealmList<Event> dataSet, RecyclerViewClickListener clickListener) {
        this.context = context;
        this.events = dataSet;
        this.mListener = clickListener;
    }

    public static class SuggestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView performerView;
        public TextView venueView;
        public TextView dateView;
        private RecyclerViewClickListener mListener;
        public SuggestionViewHolder(View v, RecyclerViewClickListener listener){
            super(v);
            performerView = v.findViewById(R.id.performer_view);
            venueView = v.findViewById(R.id.venue_view);
            dateView = v.findViewById(R.id.date_view);
            mListener = listener;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    @Override
    public EventListAdapter.SuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.suggestion_cell, parent, false);
        SuggestionViewHolder vh = new SuggestionViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(SuggestionViewHolder holder, int position){
        holder.performerView.setText(events.get(position).getPerformer().getName());
        holder.dateView.setText(events.get(position).getDate());
        holder.venueView.setText(events.get(position).getVenue().getVenueName());
    }
}
