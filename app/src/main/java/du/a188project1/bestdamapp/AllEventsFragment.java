/*
Tess Julien
Fragment that will hold a list of all future events
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

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllEventsFragment extends Fragment {

    // declare variables
    private RecyclerView allEventsList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter eventsAdapter;

    public AllEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_events, container, false);

        //Establish Realm and find all events saved in Realm
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Event> allEventsRealm = realm.where(Event.class).findAll();

        //Add all event objects found in Realm to a list to be displayed in the fragment
        RealmList<Event> allEvents = new RealmList<Event>();
        allEvents.addAll(allEventsRealm.subList(0, allEventsRealm.size()));
        allEventsList = (RecyclerView)view.findViewById(R.id.all_events_list);

        //set up the layout manager
        layoutManager = new LinearLayoutManager(getContext());
        allEventsList.setLayoutManager(layoutManager);

        //make a MainActivity variable that data will be pulled from and passed to the next activity
        final MainActivity mainActivity = (MainActivity) this.getActivity();

        //clicking on an event in the list will open up an Event Activity
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //determine which event is selected
                Event event = (Event) allEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                //pass event id and user id to the next activity
                intent.putExtra("event", event.getId());
                intent.putExtra("current_email", mainActivity.user.getEmail());
                startActivity(intent);
            }

        };


        //set the adapter
        eventsAdapter = new EventListAdapter(getContext(), allEvents, listener);
        allEventsList.setAdapter(eventsAdapter);

        return view;
    }

}
