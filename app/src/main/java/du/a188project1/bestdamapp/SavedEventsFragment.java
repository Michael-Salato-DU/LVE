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

import io.realm.RealmList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedEventsFragment extends Fragment {

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

        RealmList<Event> savedEvents = new RealmList<Event>();
        savedEventsList = (RecyclerView)view.findViewById(R.id.saved_events_list);
//
//        Event event1 = new Event();
//        Band BandPerry = new Band();
//        Venue Woolys = new Venue();
//        BandPerry.setName("The Band Perry");
//        Woolys.setVenueName("Woolys");
//        event1.setPerformer(BandPerry);
//        event1.setDate("11/3/2018");
//        event1.setVenue(Woolys);
//
//        savedEvents.add(event1);

        layoutManager = new LinearLayoutManager(getContext());
        savedEventsList.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = (Event) savedEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                intent.putExtra("event", event.getId());
                startActivity(intent);
            }
        };

        eventsAdapter = new EventListAdapter(getContext(), savedEvents, listener);
        savedEventsList.setAdapter(eventsAdapter);

        return view;
    }

}
