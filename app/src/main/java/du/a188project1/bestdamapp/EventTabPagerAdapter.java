/* Michael Salato
   A tab pager adapter for EventActivity.
 */

package du.a188project1.bestdamapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class EventTabPagerAdapter extends FragmentStatePagerAdapter{
    int mNumOfTabs;

    public EventTabPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                EventProfileFragment tab1 = new EventProfileFragment();
                return tab1;
            case 1:
                BandProfileFragment tab2 = new BandProfileFragment();
                return tab2;
            case 2:
                ReviewFragment tab3 = new ReviewFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
