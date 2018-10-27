/*
Tess Julien
October 8, 2018
Landing page of app with three tabs. Selecting a tab opens up a fragment.
 */
package du.a188project1.bestdamapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

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
                review1.setMessage("I don't know how else to put it. This performance" +
                        " was simply amazing!");
                review1.setImage(getBitMapData(R.drawable.review_image0,280,200));

                Review review2 = new Review();
                review2.setId("review2");
                review2.setMessage("So energetic!");
                review2.setImage(getBitMapData(R.drawable.review_image1,280,200));

                Review review3 = new Review();
                review3.setId("review3");
                review3.setMessage("Meh.");
                review3.setImage(getBitMapData(R.drawable.review_image2,280,200));

                bandReviews.add(review1);
                bandReviews.add(review2);
                bandReviews.add(review3);

                // Create a list of Images to use for each performer.
                // Will clear this list after setting it for each performer.
                RealmList<Image> bandImages = new RealmList<Image>();

                // Create events and populate them with data
                // event 1
                Event event1 = new Event();
                Band CoryOren = new Band();
                Venue Vaudeville = new Venue();
                Vaudeville.setVenueName("Vaudeville");
                CoryOren.setName("Cory Oren");
                CoryOren.setGenre("Pop");
                CoryOren.setUser_rating(3);
                CoryOren.setDescription("Cory is a singer who sings.");
                CoryOren.setReviews(bandReviews);
                // Set Images for this performer
                Image CoryOrenImage1 = new Image();
                Image CoryOrenImage2 = new Image();
                CoryOrenImage1.setImage(getBitMapData(R.drawable.cory_oren0,600,338));
                CoryOrenImage2.setImage(getBitMapData(R.drawable.cory_oren1,600,338));
                bandImages.add(CoryOrenImage1);
                bandImages.add(CoryOrenImage2);
                CoryOren.setPictures(bandImages);
                event1.setPerformer(CoryOren);
                event1.setDate("10/30/2018");
                event1.setVenue(Vaudeville);
                event1.setId("OrenVaudeville");
                event1.setMinPrice(25);
                event1.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event1);
                bandImages.clear(); // clear bandImages to use for the next band

                // event2
                Event event2 = new Event();
                Band BandPerry = new Band();
                Venue Woolys = new Venue();
                BandPerry.setName("The Band Perry");
                BandPerry.setGenre("Jazz");
                BandPerry.setUser_rating(4);
                BandPerry.setDescription("Perry is a band that plays together.");
                BandPerry.setReviews(bandReviews);
                // Set Images for this performer
                Image BandPerryImage1 = new Image();
                Image BandPerryImage2 = new Image();
                BandPerryImage1.setImage(getBitMapData(R.drawable.band_perry0,600,338));
                BandPerryImage2.setImage(getBitMapData(R.drawable.band_perry1,600,338));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(BandPerryImage1);
                bandImages.add(BandPerryImage2);
                BandPerry.setPictures(bandImages);
                Woolys.setVenueName("Woolys");
                event2.setPerformer(BandPerry);
                event2.setDate("11/3/2018");
                event2.setVenue(Woolys);
                event2.setId("PerryWoolys");
                event2.setMinPrice(30);
                event2.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event2);
                bandImages.clear(); // clear bandImages to use for the next band

                // event3
                Event event3 = new Event();
                Band TaylorSwift = new Band();
                Venue SportsPlace = new Venue();
                TaylorSwift.setName("Taylor Swift");
                TaylorSwift.setGenre("Indie");
                TaylorSwift.setUser_rating(5);
                TaylorSwift.setDescription("T-Swift is the best!");
                TaylorSwift.setReviews(bandReviews);
                // Set Images for this performer
                Image TaylorSwiftImage1 = new Image();
                Image TaylorSwiftImage2 = new Image();
                TaylorSwiftImage1.setImage(getBitMapData(R.drawable.taylor_swift0,600,338));
                TaylorSwiftImage2.setImage(getBitMapData(R.drawable.taylor_swift1,600,338));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(TaylorSwiftImage1);
                bandImages.add(TaylorSwiftImage2);
                TaylorSwift.setPictures(bandImages);
                SportsPlace.setVenueName("Whatever it's called");
                event3.setPerformer(TaylorSwift);
                event3.setDate("10/15/2018");
                event3.setVenue(SportsPlace);
                event3.setId("TaylorTest");
                event3.setMinPrice(80);
                event3.setTicketLink("https://www.amazon.com/");
                realm.copyToRealmOrUpdate(event3);
                bandImages.clear(); // clear bandImages to use for the next band
            }
        });
    }

    // Resize images and convert to byte[]
    public byte[] getBitMapData(int image, int newWidth, int newHeight){

        // Source for getting bitMap from drawable: Drawable to byte[]
        // https://stackoverflow.com/questions/4435806/drawable-to-byte
        // user: Kalpesh
        // date: July 16, 2012
        Bitmap bitMap = BitmapFactory.decodeResource(getResources(), image);

        // Source for scaling bitMap: Resize Drawable in Android
        // https://stackoverflow.com/questions/7021578/resize-drawable-in-android
        // user: craned
        // date: May 9, 2014
        Bitmap bitMapScaled = Bitmap.createScaledBitmap(bitMap, newWidth, newHeight, true);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitMapScaled.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        return bitMapData;
    }
}
