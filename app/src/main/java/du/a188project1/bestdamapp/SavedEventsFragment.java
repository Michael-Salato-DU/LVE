/*
Tess Julien
October 8, 2018
Fragment that will hold a list of user's saved events
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

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedEventsFragment extends Fragment {

    //declare variables
    private RecyclerView savedEventsList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter eventsAdapter;

    public SavedEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_events, container, false);

        // create MainActivity instance
        final MainActivity mainActivity = (MainActivity) this.getActivity();

        // get list of events from the user and use it to populate the display
        RealmList<Event> savedEvents = mainActivity.user.getSaved_events();
        savedEventsList = (RecyclerView)view.findViewById(R.id.saved_events_list);

        layoutManager = new LinearLayoutManager(getContext());
        savedEventsList.setLayoutManager(layoutManager);
        refreshList();

        return view;
    }

    // refresh list when user comes back to the fragment
    @Override
    public void onResume(){
        super.onResume();
        refreshList();
    }

    //function to make sure list is up to date
    private void refreshList(){
        Realm realm = Realm.getDefaultInstance();
        MainActivity activity = (MainActivity) this.getActivity();
        final RealmList<Event> savedEvents = activity.user.getSaved_events();
        final MainActivity mainActivity = (MainActivity) this.getActivity();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            // open an event activity for the event selected from the list
            @Override
            public void onClick(View view, int position) {
                Event event = (Event) savedEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                // pass event and user data to the event activity
                intent.putExtra("event", event.getId());
                intent.putExtra("current_email", mainActivity.user.getEmail());
                startActivity(intent);
            }
        };
        eventsAdapter = new EventListAdapter(getContext(), savedEvents, listener);
        savedEventsList.setAdapter(eventsAdapter);
    }

}
