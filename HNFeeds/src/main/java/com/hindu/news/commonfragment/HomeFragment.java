package com.hindu.news.commonfragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.hindu.news.R;
import com.hindu.news.commonrssfeeds.RssItem;
import com.hindu.news.commonrssfeeds.RssReader;
import com.hindu.news.commonui.CommonAdapter;
import com.hindu.news.commonui.CommonRowData;
import com.hindu.news.commonui.PopulationListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mageswari on 16-05-2015.
 */
public class HomeFragment extends Fragment{
    private CommonAdapter homeCommonAdapter;
    private List<CommonRowData> homeCommonDataList;
    private CommonRowData homeCommonRowData;

    private ListView homeCommonListView;
    private ProgressBarCircularIndeterminate progressBarVew;
    private List<RssItem> rssItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainment_screen, container,
                false);
        setUpView(view);
        setUpAdapter();

        new HomeRssFeed().execute("http://www.pcworld.com/news.rss");

        return view;
    }

    private void setUpView(View view) {
        homeCommonListView=(ListView)view.findViewById(R.id.commonListView);
        progressBarVew=(ProgressBarCircularIndeterminate)view.findViewById(R.id.progressBarCircularIndeterminate);

    }

    private void setUpAdapter()
    {

        homeCommonDataList = new ArrayList<CommonRowData>();
        homeCommonAdapter = new CommonAdapter(getActivity(), homeCommonDataList);

        int[] viewIDs = { R.id.imageDisplay, R.id.imageDisplaySec, R.id.shortTxtView,
                R.id.shortTxtViewSec, R.id.leftLayout,
                R.id.rightLayout };
        homeCommonAdapter.setLayoutTextViews(R.layout.listviewdoubleitem, viewIDs);
        homeCommonAdapter.setPopulationListener(new PopulationListener() {

            @Override
            public void populateFrom(View v, int position,
                                     final CommonRowData list, final View[] views) {

                ((TextView) views[2]).setText(list.getHead1());
                ((TextView) views[3]).setText(list.getHead2());
                if(!list.getHead3().equals(""))
                Picasso.with(getActivity()).load(R.drawable.icon).into((ImageView) views[0]);
                Picasso.with(getActivity()).load(list.getHead3()).into((ImageView) views[0]);
                if(!list.getHead4().equals(""))
                    Picasso.with(getActivity()).load(R.drawable.icon).into((ImageView) views[1]);
                Picasso.with(getActivity()).load(list.getHead4()).into((ImageView) views[1]);

            }

            @Override
            public void onRowCreate(View[] views) {
                // TODO Auto-generated method stub

            }
        });

        homeCommonListView.setAdapter(homeCommonAdapter);

    }


    private class HomeRssFeed extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                RssReader rssReader = new RssReader(params[0]);
                rssItems = rssReader.getItems();

            } catch (Exception e) {
                Log.v("Error Parsing Data", e + "");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            int i = 0;
            if(rssItems!=null) {
                progressBarVew.setVisibility(View.GONE);
                homeCommonListView.setVisibility(View.VISIBLE);
                while (i < rssItems.size() - 1) {
                    homeCommonRowData = new CommonRowData();
                    String lefTitle = rssItems.get(i).getTitle();
                    System.out.println("get the image url..."+rssItems.get(i).getImageUrl());
                    String leftImageView = rssItems.get(i).getImageUrl();

                    i++;
                    String rightTitle = rssItems.get(i).getTitle();
                    String rightImageView = rssItems.get(i).getImageUrl();


                    homeCommonRowData.setHead1(lefTitle);
                    homeCommonRowData.setHead2(rightTitle);
                    homeCommonRowData.setHead3(leftImageView);
                    homeCommonRowData.setHead4(rightImageView);


                    homeCommonAdapter.add(homeCommonRowData);
                    i++;
                }
                homeCommonAdapter.notifyDataSetChanged();
            }
        }
    }
}
