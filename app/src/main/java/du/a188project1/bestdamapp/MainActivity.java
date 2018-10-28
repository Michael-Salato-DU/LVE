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
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm realm = Realm.getDefaultInstance();
        // if user clicked submit from GenreSelection (they logged in through the log in page)
        if (getIntent().getStringExtra("current_email") != null){
            // get the email passed from the intent
            String current_email = getIntent().getStringExtra("current_email");
            // get the realm User that has this email
            user = realm.where(User.class).equalTo("email",current_email).findFirst();
        }
        // else open a file to get the email for User (they opened the app without going through the log in page)
        else {
            // Open the file that contains the most recent user's email.
            // Source for FileInputStream and read() function: https://developer.android.com/reference/java/io/FileInputStream
            // Source for openFileInput() function: https://developer.android.com/reference/android/content/Context#openFileInput(java.lang.String)
            // Source for using the read() function and converting to string: https://examples.javacodegeeks.com/core-java/io/fileinputstream/read-file-with-fileinputstream/
            // ####################################
            String filename = "current_user_email.txt"; // the filename
            String current_email = ""; // define an empty string to hold the email
            int ch; // will hold each byte of data as it is read in from the file

            FileInputStream inputStream; // create a FileInputStream

            try {
                inputStream = openFileInput(filename); // open the file

                // Read bytes of data from this input stream until EOF (-1) is reached
                while((ch = inputStream.read()) != -1) {
                    current_email += (char)ch; // convert byte to char and concatenate with current_email string
                }
                inputStream.close(); // close the file

                // get the realm User that has this email
                user = realm.where(User.class).equalTo("email",current_email).findFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ####################################
        }

        // logs for checking that the correct User is active
        Log.d("UserName", user.getFirst_name());
        Log.d("UserEmail", user.getEmail());

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
                // ** All image sources are found in "image_sources.txt" in the assets folder **

                // Create a list of reviews to use for each performer.
                RealmList<Review> bandReviews = new RealmList<Review>();

                Review review1 = new Review();
                review1.setId("review1");
                review1.setMessage("I don't know how else to put it. This performance" +
                        " was simply amazing!");
                review1.setImage(getBitMapData(R.drawable.review_image0));

                Review review2 = new Review();
                review2.setId("review2");
                review2.setMessage("So energetic!");
                review2.setImage(getBitMapData(R.drawable.review_image1));

                Review review3 = new Review();
                review3.setId("review3");
                review3.setMessage("Meh.");
                review3.setImage(getBitMapData(R.drawable.review_image2));

                bandReviews.add(review1);
                bandReviews.add(review2);
                bandReviews.add(review3);
              
                // Create a list of Images to use for each performer.
                // Will clear this list after setting it for each performer.
                RealmList<Image> bandImages = new RealmList<Image>();

                Event colonyHouseWoolys = new Event();
                Band colonyHouse = new Band();
                Venue woolys = new Venue();
                colonyHouse.setName("Colony House");
                colonyHouse.setGenre("Rock");
                colonyHouse.setDescription("Colony House, a humble apartment complex on 11th Ave. in downtown Franklin, Tennessee, has at some point in each of our lives been our home. Now it is our namesake as we take Franklin, TN with us and travel around the world playing music for those who will listen!");
                colonyHouse.setReviews(bandReviews);
                colonyHouse.setUser_rating(5);
                // Set Images for this performer
                Image colonyHouseImage1 = new Image();
                Image colonyHouseImage2 = new Image();
                colonyHouseImage1.setImage(getBitMapData(R.drawable.colony_house0));
                colonyHouseImage2.setImage(getBitMapData(R.drawable.colony_house1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(colonyHouseImage1);
                bandImages.add(colonyHouseImage2);
                colonyHouse.setPictures(bandImages);
                woolys.setVenueName("Wooly's");
                colonyHouseWoolys.setId("colonyHouse102918");
                colonyHouseWoolys.setPerformer(colonyHouse);
                colonyHouseWoolys.setVenue(woolys);
                colonyHouseWoolys.setDate("10/29/2018");
                colonyHouseWoolys.setMinPrice(16);
                colonyHouseWoolys.setMaxPrice(18);
                colonyHouseWoolys.setTicketLink("https://www.ticketfly.com/purchase/event/1695445");
                realm.copyToRealmOrUpdate(colonyHouseWoolys);
                bandImages.clear(); // clear bandImages to use for the next band

                Event johnathanDavisWoolys = new Event();
                Band johnathanDavis = new Band();
                johnathanDavis.setName("Jonathan Davis");
                johnathanDavis.setGenre("Metal");
                johnathanDavis.setDescription("None available");
                johnathanDavis.setReviews(bandReviews);
                johnathanDavis.setUser_rating(4);
                // Set Images for this performer
                Image johnathanDavisImage1 = new Image();
                Image johnathanDavisImage2 = new Image();
                johnathanDavisImage1.setImage(getBitMapData(R.drawable.jonathan_davis0));
                johnathanDavisImage2.setImage(getBitMapData(R.drawable.jonathan_davis1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(johnathanDavisImage1);
                bandImages.add(johnathanDavisImage2);
                johnathanDavis.setPictures(bandImages);
                johnathanDavisWoolys.setId("johnathanDavis102018");
                johnathanDavisWoolys.setPerformer(johnathanDavis);
                johnathanDavisWoolys.setVenue(woolys);
                johnathanDavisWoolys.setDate("10/20/2018");
                johnathanDavisWoolys.setMinPrice(29);
                johnathanDavisWoolys.setMaxPrice(32);
                johnathanDavisWoolys.setTicketLink("https://www.ticketfly.com/purchase/event/1757718");
                realm.copyToRealmOrUpdate(johnathanDavisWoolys);
                bandImages.clear(); // clear bandImages to use for the next band

                Event nedLedouxWoolys = new Event();
                Band nedLedoux = new Band();
                nedLedoux.setName("Ned leDoux");
                nedLedoux.setGenre("Country");
                nedLedoux.setDescription("In country music, a last name like LeDoux casts a big, storied and bittersweet shadow, but it’s one Ned LeDoux doesn’t mind standing in one bit. Having been a drummer in his dad Chris’ band Western Underground since 1998, Ned knew from an early age that he had “no plan b” but to play music, “Once I got the taste of the road, and being in front of a crowd and just the sound of it, it was...freedom.” The timing couldn’t be more right for Ned to pick up a guitar and belt out “Western Skies;” it has been over 10 years since Chris LeDoux passed and he believes people want to hear something new. Ned has boxes of song ideas his dad never finished and is digging through those for inspiration, “I will kind of stick with what dad used to do but bring my own stuff to the table.” In July of 2015, Ned traveled to Nashville with some of those unfinished songs and met up with Mac McAnally to put that inspiration to work. Mac produced Chris’ last two studio records and wrote his hit “Horsepower,” so the collaboration with Ned was a natural fit and lead to the first new Chris LeDoux co-write in nearly two decades.");
                nedLedoux.setReviews(bandReviews);
                nedLedoux.setUser_rating(5);
                // Set Images for this performer
                Image nedLedouxImage1 = new Image();
                Image nedLedouxImage2 = new Image();
                nedLedouxImage1.setImage(getBitMapData(R.drawable.ned_ledoux0));
                nedLedouxImage2.setImage(getBitMapData(R.drawable.ned_ledoux1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(nedLedouxImage1);
                bandImages.add(nedLedouxImage2);
                nedLedoux.setPictures(bandImages);
                nedLedouxWoolys.setId("nedLedoux110118");
                nedLedouxWoolys.setPerformer(nedLedoux);
                nedLedouxWoolys.setVenue(woolys);
                nedLedouxWoolys.setDate("11/01/2018");
                nedLedouxWoolys.setMinPrice(15);
                nedLedouxWoolys.setMaxPrice(18);
                nedLedouxWoolys.setTicketLink("https://www.ticketfly.com/purchase/event/1771092");
                realm.copyToRealmOrUpdate(nedLedouxWoolys);
                bandImages.clear(); // clear bandImages to use for the next band

                Event suicideGirlsWoolys = new Event();
                Band suicideGirls = new Band();
                suicideGirls.setName("SuicideGirls: Blackheart Burlesque");
                suicideGirls.setGenre("Dance");
                suicideGirls.setDescription("SuicideGirls: Blackheart Burlesque is the sexiest, smartest, geekiest, and most fun definitive pop-culture burlesque show! Featuring none other than SuicideGirls themselves, the show has been performed hundreds of times, delighted millions of fans in over six countries, and has been touring since 2003! Don’t miss your chance to see it live during the 2018 US Tour! SuicideGirls has been redefining beauty since 2001 with its network of over 3,000 SuicideGirls and almost 9 million pinup-style photographs on the premier, members-only social network SuicideGirls.com. Check out blackheartburlesque.com for more information about the tour and to see where the girls are headed next!");
                suicideGirls.setReviews(bandReviews);
                suicideGirls.setUser_rating(4);
                // Set Images for this performer
                Image suicideGirlsImage1 = new Image();
                Image suicideGirlsImage2 = new Image();
                suicideGirlsImage1.setImage(getBitMapData(R.drawable.suicide_girls0));
                suicideGirlsImage2.setImage(getBitMapData(R.drawable.suicide_girls1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(suicideGirlsImage1);
                bandImages.add(suicideGirlsImage2);
                suicideGirls.setPictures(bandImages);
                suicideGirlsWoolys.setId("suicideGirls110218");
                suicideGirlsWoolys.setPerformer(suicideGirls);
                suicideGirlsWoolys.setVenue(woolys);
                suicideGirlsWoolys.setDate("11/02/2018");
                suicideGirlsWoolys.setMinPrice(25);
                suicideGirlsWoolys.setMaxPrice(75);
                suicideGirlsWoolys.setTicketLink("https://www.ticketfly.com/purchase/event/1692597");
                realm.copyToRealmOrUpdate(suicideGirlsWoolys);
                bandImages.clear(); // clear bandImages to use for the next band

                Event bandPerryWoolys = new Event();
                Band bandPerry = new Band();
                bandPerry.setName("The Band Perry");
                bandPerry.setGenre("Country");
                bandPerry.setDescription("The Band Perry's \"modern throwback\" style combines classic Country with an eclectic infusion of Rock and Soul. As songwriters and musicians, their sound is rounded out by perfect three-part harmonies. The self-titled debut album, THE BAND PERRY, was released in October 2010 by Republic Nashville and one year later was certified Platinum.");
                bandPerry.setReviews(bandReviews);
                bandPerry.setUser_rating(5);
                // Set Images for this performer
                Image bandPerryImage1 = new Image();
                Image bandPerryImage2 = new Image();
                bandPerryImage1.setImage(getBitMapData(R.drawable.band_perry0));
                bandPerryImage2.setImage(getBitMapData(R.drawable.band_perry1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(bandPerryImage1);
                bandImages.add(bandPerryImage2);
                bandPerry.setPictures(bandImages);
                bandPerryWoolys.setId("bandPerry110318");
                bandPerryWoolys.setPerformer(bandPerry);
                bandPerryWoolys.setVenue(woolys);
                bandPerryWoolys.setDate("11/03/2018");
                bandPerryWoolys.setMinPrice(0);
                bandPerryWoolys.setMaxPrice(0);
                bandPerryWoolys.setTicketLink("http://www.woolysdm.com/event/1767246-band-perry-des-moines/");
                realm.copyToRealmOrUpdate(bandPerryWoolys);
                bandImages.clear(); // clear bandImages to use for the next band

                Event gregoryIsakovWoolys = new Event();
                Band gregoryIsakov = new Band();
                gregoryIsakov.setName("Gregory Alan Isakov");
                gregoryIsakov.setGenre("Indie");
                gregoryIsakov.setDescription("Born in Johannesburg, South Africa, and calling Colorado home, Gregory Alan Isakov has been traveling all his life. Songs that hone a masterful quality tell a story of miles and landscapes, and the search for a sense of place. His song-craft lends to deep lyrical masterpieces, with hints of his influences, Leonard Cohen and Bruce Springsteen. He has been described as “strong, subtle, a lyrical genius.” Isakov will be releasing a new album, Evening Machines, on October 5th, 2018.");
                gregoryIsakov.setReviews(bandReviews);
                gregoryIsakov.setUser_rating(4);
                // Set Images for this performer
                Image gregoryIsakovImage1 = new Image();
                Image gregoryIsakovImage2 = new Image();
                gregoryIsakovImage1.setImage(getBitMapData(R.drawable.gregory_isakov0));
                gregoryIsakovImage2.setImage(getBitMapData(R.drawable.gregory_isakov1));
                // Add Images to RealmList and set this RealmList for this performer
                bandImages.add(gregoryIsakovImage1);
                bandImages.add(gregoryIsakovImage2);
                gregoryIsakov.setPictures(bandImages);
                gregoryIsakovWoolys.setId("gregoryIsakov110418");
                gregoryIsakovWoolys.setPerformer(gregoryIsakov);
                gregoryIsakovWoolys.setVenue(woolys);
                gregoryIsakovWoolys.setDate("11/04/2018");
                gregoryIsakovWoolys.setMinPrice(25);
                gregoryIsakovWoolys.setMaxPrice(30);
                gregoryIsakovWoolys.setTicketLink("https://www.ticketfly.com/purchase/event/1726691");
                realm.copyToRealmOrUpdate(gregoryIsakovWoolys);
                bandImages.clear(); // clear bandImages to use for the next band
                                         
            }
        });
    }

    // Convert image to byte[]
    public byte[] getBitMapData(int image){

        // Source for getting bitMap from drawable: Drawable to byte[]
        // https://stackoverflow.com/questions/4435806/drawable-to-byte
        // user: Kalpesh
        // date: July 16, 2012
        Bitmap bitMap = BitmapFactory.decodeResource(getResources(), image);

        // Source for scaling bitMap: Resize Drawable in Android
        // https://stackoverflow.com/questions/7021578/resize-drawable-in-android
        // user: craned
        // date: May 9, 2014
        // Not currently using the following line of code.
//        Bitmap bitMapScaled = Bitmap.createScaledBitmap(bitMap, newWidth, newHeight, true);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();

        return bitMapData;
    }
}
