package du.a188project1.bestdamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class GenreSelection extends AppCompatActivity {
    //Declare
    public User user;
    private RecyclerView genreList;
    private RecyclerView.LayoutManager layoutManager;
    private Button genreButton;
    private Button spotifyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_selection);
        RealmList<String> selectedGenre = new RealmList<String>();
        ArrayList<String> spotify_list = new ArrayList<String>();

        genreList = (RecyclerView) findViewById(R.id.genre_list);
        layoutManager = new LinearLayoutManager(getBaseContext());
        genreList.setLayoutManager(layoutManager);
        genreButton = (Button) findViewById(R.id.genre_button);
        spotifyButton = (Button) findViewById(R.id.spotify_button);

        //Create a list of genres to fill in text in checkboxes
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
        //Checkbox listener that looks for change and adds checked checkbox text to selectedGenre list
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

        //Create adapter that will update adapter dataset with Genres and attach listener to ViewHolder
        final GenreSelectionAdapter adapter = new GenreSelectionAdapter(getBaseContext(), Genres, listener);
        spotifyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            }
        });
        genreList.setAdapter(adapter);

        //Button that submits selected genres and switch to MainActivity
        genreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Selected Genre(s): ", selectedGenre.toString());

                Realm realm = Realm.getDefaultInstance();
                String current_email = getIntent().getStringExtra("current_email");
                Log.v("Current Email ", current_email);

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        user = realm.where(User.class).equalTo("email",current_email).findFirst();
                        user.setGenre_list(selectedGenre);
                        realm.copyToRealmOrUpdate(user);
                    }
                });

                Intent main_intent = new Intent(getBaseContext(), MainActivity.class);
                main_intent.putExtra("current_email", user.getEmail());
                startActivity(main_intent);
            }
        });
    }

}
