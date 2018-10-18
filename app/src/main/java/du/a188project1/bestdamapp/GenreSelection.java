package du.a188project1.bestdamapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class GenreSelection extends AppCompatActivity {

    private RecyclerView genreList;
    private RecyclerView.LayoutManager layoutManager;
    private Button genreButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_selection);

        final ArrayList<String> selectedGenre = new ArrayList<String>();

        genreList = (RecyclerView) findViewById(R.id.genre_list);
        layoutManager = new LinearLayoutManager(getBaseContext());
        genreList.setLayoutManager(layoutManager);
        genreButton = (Button) findViewById(R.id.genre_button);

        ArrayList<String> Genres = new ArrayList<String>();
        Genres.add("Blues");
        Genres.add("Country");
        Genres.add("Disco");
        Genres.add("EDM");
        Genres.add("Folk");
        Genres.add("Hip Hop");
        Genres.add("Indie");
        Genres.add("Jazz");
        Genres.add("Metal");
        Genres.add("Orchestra");
        Genres.add("Pop");
        Genres.add("Rock");
        Genres.add("Rap");
        Genres.add("R&B");


        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Log.v("Checked", compoundButton.getText().toString());
                    selectedGenre.add(compoundButton.getText().toString());
                }
                else {
                    Log.v("Checked", "Removed " + compoundButton.getText().toString());
                    selectedGenre.remove(compoundButton.getText().toString());
                }
            }
        };

        final GenreSelectionAdapter adapter = new GenreSelectionAdapter(getBaseContext(), Genres, listener);
        genreList.setAdapter(adapter);


        genreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Selected Genre(s): ", selectedGenre.toString());

                //TODO: Use the current user instead of new
                User user = new User(); //subject to change: temporary new user; user will be the set user
                user.setGenre_list(selectedGenre);

                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                intent.putExtra("selectedGenres", selectedGenre);
                startActivity(intent);
            }
        });
    }

}
