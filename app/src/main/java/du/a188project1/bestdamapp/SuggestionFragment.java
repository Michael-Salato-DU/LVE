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

import java.io.Serializable;
import java.util.ArrayList;

import io.realm.RealmList;


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

        RealmList<Event> suggestedEvents = new RealmList<Event>();
        suggestionList = (RecyclerView)view.findViewById(R.id.suggestion_list);

        layoutManager = new LinearLayoutManager(getContext());
        suggestionList.setLayoutManager(layoutManager);

        final MainActivity mainActivity = (MainActivity) this.getActivity();

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = (Event) suggestedEvents.get(position);
                Intent intent = new Intent(view.getContext(), EventActivity.class);
                intent.putExtra("event", event.getId());
                intent.putExtra("current_email", mainActivity.user.getEmail());
                startActivity(intent);
            }
        };

        suggestionAdapter = new EventListAdapter(getContext(), suggestedEvents, listener);
        suggestionList.setAdapter(suggestionAdapter);

        return view;
    }

}
