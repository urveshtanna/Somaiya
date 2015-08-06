package urvesh.com.somaiya;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import urvesh.com.somaiya.subTabs_frags.frag_science_1;
import urvesh.com.somaiya.subTabs_frags.frag_science_2;
import urvesh.com.somaiya.subTabs_frags.frag_science_3;
import urvesh.com.somaiya.subTabs_frags.frag_science_4;

public class Science_main extends ActionBarActivity implements MaterialTabListener {

    private Toolbar mToolbar;
    private MaterialTabHost mTabHost;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_main);
        mToolbar = (Toolbar) findViewById(R.id.app_bar_science2);
        setSupportActionBar(mToolbar);




        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);


        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);

            }
        });
        for(int i = 0; i < mAdapter.getCount();i++){
            mTabHost.addTab(
                    mTabHost.newTab()
                        .setText(mAdapter.getPageTitle(i).toString().toLowerCase())
                        .setTabListener(this)
            );
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        mViewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {




        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            ;
        }

        public Fragment getItem(int num) {
            switch (num){
                case 0:
                    return new frag_science_1();
                case 1:
                    return new frag_science_2();
                case 2:
                    return new frag_science_3();
                case 3:
                    return new frag_science_4();
                default:
                    break;
            }
            return null;

        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.science)[position];
        }
    }
}