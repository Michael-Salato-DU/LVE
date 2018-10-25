/*
Tess Julien
October 8, 2018
Landing page of app with three tabs. Selecting a tab opens up a fragment.
 */
package du.a188project1.bestdamapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm realm = Realm.getDefaultInstance();
        String current_email = getIntent().getStringExtra("current_email");
        user = realm.where(User.class).equalTo("email",current_email).findFirst();

        final RealmResults<Event> events = realm.where(Event.class).findAll();
        RealmList<Event> allEvents = new RealmList<Event>();
        allEvents.addAll(events.subList(0, events.size()));
        if(allEvents.size()==0) {
            populateEvents();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Coordinator Layout automatically comes with a Floating Action Button. I left the code here in case we want to add it later.
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Set up 3 tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Suggestions"));
        tabLayout.addTab(tabLayout.newTab().setText("All Events"));
        tabLayout.addTab(tabLayout.newTab().setText("Your Events"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        final TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void populateEvents(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Create a list of reviews to use for each performer.
                RealmList<Review> bandReviews = new RealmList<Review>();

                Review review1 = new Review();
                review1.setId("review1");
                review1.setMessage("Amazing!");

                Review review2 = new Review();
                review2.setId("review2");
                review2.setMessage("So energetic!");

                Review review3 = new Review();
                review3.setId("review3");
                review3.setMessage("Meh.");

                Review review4 = new Review();
                review4.setId("review4");
                review4.setMessage("I liked it.");

                Review review5 = new Review();
                review5.setId("review5");
                review5.setMessage("It was aight.");

                bandReviews.add(review1);
                bandReviews.add(review2);
                bandReviews.add(review3);
                bandReviews.add(review4);
                bandReviews.add(review5);

                Event event1 = new Event();
                Band CoryOren = new Band();
                Venue Vaudeville = new Venue();
                Vaudeville.setVenueName("Vaudeville");
                CoryOren.setName("Cory Oren");
                CoryOren.setGenre("Pop");
                CoryOren.setUser_rating(3);
                CoryOren.setDescription("Cory is a singer who sings.");
                CoryOren.setReviews(bandReviews);
                event1.setPerformer(CoryOren);
                event1.setDate("10/30/2018");
                event1.setVenue(Vaudeville);
                event1.setId("OrenVaudeville");
                event1.setMinPrice(25);
                event1.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event1);

                Event event2 = new Event();
                Band BandPerry = new Band();
                Venue Woolys = new Venue();
                BandPerry.setName("The Band Perry");
                BandPerry.setGenre("Jazz");
                BandPerry.setUser_rating(4);
                BandPerry.setDescription("Perry is a band that plays together.");
                BandPerry.setReviews(bandReviews);
                Woolys.setVenueName("Woolys");
                event2.setPerformer(BandPerry);
                event2.setDate("11/3/2018");
                event2.setVenue(Woolys);
                event2.setId("PerryWoolys");
                event2.setMinPrice(30);
                event2.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event2);

                Event event3 = new Event();
                Band TaylorSwift = new Band();
                Venue SportsPlace = new Venue();
                TaylorSwift.setName("Taylor Swift");
                TaylorSwift.setGenre("Indie");
                TaylorSwift.setUser_rating(5);
                TaylorSwift.setDescription("T-Swift is the best!");
                TaylorSwift.setReviews(bandReviews);
                SportsPlace.setVenueName("Whatever it's called");
                event3.setPerformer(TaylorSwift);
                event3.setDate("10/15/2018");
                event3.setVenue(SportsPlace);
                event3.setId("TaylorTest");
                event3.setMinPrice(80);
                event3.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event3);
            }
        });
    }
}
