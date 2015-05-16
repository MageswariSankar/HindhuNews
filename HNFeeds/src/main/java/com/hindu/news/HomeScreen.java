package com.hindu.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hindu.news.commonfragment.BusinessFragment;
import com.hindu.news.commonfragment.EntertainmentFragment;
import com.hindu.news.commonfragment.HomeFragment;
import com.hindu.news.commonfragment.SportsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

/**
 * Created by Mageswari on 16-05-2015.
 */
public class HomeScreen extends AppCompatActivity {

    private SmartTabLayout smartTabLayoutObj;
    private ViewPager viewPagerObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        setupViews();
        addFragmentToPager();

    }
    private void setupViews()
    {

        smartTabLayoutObj=(SmartTabLayout)findViewById(R.id.newsViewPagerTab);
        viewPagerObj=(ViewPager)findViewById(R.id.newsViewPager);
    }
    private void addFragmentToPager()
    {
       FragmentPagerItemAdapter fragmentPagerItemAdapter=new FragmentPagerItemAdapter(getSupportFragmentManager(),
                          FragmentPagerItems.with(this)
                        .add(R.string.entment_name, EntertainmentFragment.class)
                        .add(R.string.business_name, BusinessFragment.class)
                        .add(R.string.home_name, HomeFragment.class)
                        .add(R.string.news_name, BusinessFragment.class)
                        .add(R.string.sports_name, SportsFragment.class)
                        .create());
        viewPagerObj.setAdapter(fragmentPagerItemAdapter);
        viewPagerObj.setCurrentItem(2);

        smartTabLayoutObj.setViewPager(viewPagerObj);
    }

}
