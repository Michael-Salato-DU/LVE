/*
Tess Julien
October 8, 2018
Fragment that will hold a list of suggested events
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
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionFragment extends Fragment {

    private RecyclerView suggestionList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter suggestionAdapter;

    public SuggestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);

        ArrayList<Event> suggestedEvents = new ArrayList<Event>();
        suggestionList = (RecyclerView)view.findViewById(R.id.suggestion_list);

        Event event1 = new Event();
        Band CoryOren = new Band();
        Venue Vaudeville = new Venue();
        Vaudeville.setVenueName("Vaudeville");
        CoryOren.setName("Cory Oren");
        event1.setPerformer(CoryOren);
        event1.setDate("10/30/2018");
        event1.setVenue(Vaudeville);

        Event event2 = new Event();
        Band BandPerry = new Band();
        Venue Woolys = new Venue();
        BandPerry.setName("The Band Perry");
        Woolys.setVenueName("Woolys");
        event2.setPerformer(BandPerry);
        event2.setDate("11/3/2018");
        event2.setVenue(Woolys);

        suggestedEvents.add(event1);
        suggestedEvents.add(event2);

        layoutManager = new LinearLayoutManager(getContext());
        suggestionList.setLayoutManager(layoutManager);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = (Event) suggestedEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                intent.putExtra("event", (Serializable)event);
                startActivity(intent);
            }
        };

        suggestionAdapter = new SuggestionAdapter(getContext(), suggestedEvents, listener);
        suggestionList.setAdapter(suggestionAdapter);

        return view;
    }

}
