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

        final MainActivity mainActivity = (MainActivity) this.getActivity();

        RealmList<Event> savedEvents = mainActivity.user.getSaved_events();
        savedEventsList = (RecyclerView)view.findViewById(R.id.saved_events_list);

        layoutManager = new LinearLayoutManager(getContext());
        savedEventsList.setLayoutManager(layoutManager);
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
        MainActivity activity = (MainActivity) this.getActivity();
        final RealmList<Event> savedEvents = activity.user.getSaved_events();
        final MainActivity mainActivity = (MainActivity) this.getActivity();
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = (Event) savedEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                intent.putExtra("event", event.getId());
                intent.putExtra("current_email", mainActivity.user.getEmail());
                startActivity(intent);
            }
        };
        eventsAdapter = new EventListAdapter(getContext(), savedEvents, listener);
        savedEventsList.setAdapter(eventsAdapter);
    }

}
