/* Michael Salato
   EventActivity to control tabs related to the event and band.
   Selecting a tab opens up a fragment.
 */

package du.a188project1.bestdamapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;


public class EventActivity extends AppCompatActivity {

    // Declare variables
    public User user;
    public Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up 3 tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Event Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Band Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Setup ViewPager and EventTabPagerAdapter.
        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        final EventTabPagerAdapter adapter = new EventTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // Setup listeners for selecting tabs
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

        // Get realm instance
        Realm realm = Realm.getDefaultInstance();
        // Get the user with the email address passed from MainActivity
        String currentEmail = (String) getIntent().getStringExtra("current_email");
        user = realm.where(User.class).equalTo("email", currentEmail).findFirst();

        // Get the event with the eventID passed from MainActivity
        String eventID = (String) getIntent().getStringExtra("event");
        event = realm.where(Event.class).equalTo("id", eventID).findFirst();
}

}