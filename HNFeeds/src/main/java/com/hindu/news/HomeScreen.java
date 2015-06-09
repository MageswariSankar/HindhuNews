package com.hindu.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.LinearLayout;

import com.hindu.news.commonfragment.BusinessFragment;
import com.hindu.news.commonfragment.EntertainmentFragment;
import com.hindu.news.commonfragment.HomeFragment;
import com.hindu.news.commonfragment.NewsFragment;
import com.hindu.news.commonfragment.OpinonFragment;
import com.hindu.news.commonfragment.SportsFragment;
import com.hindu.news.menu.MenuBarActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Created by Mageswari on 16-05-2015.
 */
public class HomeScreen extends MenuBarActivity {

    private SmartTabLayout smartTabLayoutObj;
    private ViewPager viewPagerObj;
    private int getPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.base_menu_layout);
        setupBaseView();
        setupViews();
       // setContentView(R.layout.home_screen);
       // setupViews();
        addFragmentToPager();

    }

    private void setupViews() {
        attachContent(R.layout.home_screen);
        //setPageTitle(getString(MU_HEADING));
        setMenuSelection(currentMenuPosition);


        smartTabLayoutObj = (SmartTabLayout) findViewById(R.id.newsViewPagerTab);
        viewPagerObj = (ViewPager) findViewById(R.id.newsViewPager);
    }

    private void addFragmentToPager() {
        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.opinon_name, OpinonFragment.class)
                        .add(R.string.entment_name, EntertainmentFragment.class)
                        .add(R.string.business_name, BusinessFragment.class)
                        .add(R.string.home_name, HomeFragment.class)
                        .add(R.string.news_name, NewsFragment.class)
                        .add(R.string.sports_name, SportsFragment.class)

                        .create());
        viewPagerObj.setAdapter(fragmentPagerItemAdapter);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("MOVE_ID") != 0) {
                getPos = getIntent().getExtras().getInt("MOVE_ID");

            } else {
                getPos = getIntent().getIntExtra("MOVE_ID", 3);
            }
        }
        System.out.println("get position id..."+getPos);
        if(getPos==0) {
            viewPagerObj.setCurrentItem(3);
        }
        else {
            if(getPos<=3)
            viewPagerObj.setCurrentItem(getPos - 1);
            else
            viewPagerObj.setCurrentItem(getPos);
        }
        viewPagerObj.setOffscreenPageLimit(1);

        smartTabLayoutObj.setViewPager(viewPagerObj);

    }

}
