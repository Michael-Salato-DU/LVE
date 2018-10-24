/* Michael Salato
   EventActivity to control tabs related to the event and band.
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

    // Declare class variables
    // find better names for static xml variables
    private ImageView imageView; // change name
    private TextView bandNameView;
    private TextView venueView;
    private TextView genreView;
    private TextView priceView;
    private TextView bandDescView;
    private Button followButton;
    private Button buyTicketsButton;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up 3 tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Event Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Band Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        final EventTabPagerAdapter adapter = new EventTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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

        // Get the Event object passed as an intent extra from MainActivity.
        final Event event = (Event) getIntent().getSerializableExtra("event");
    }

}