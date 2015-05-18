package com.hindu.news.commonfragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.hindu.news.R;
import com.hindu.news.WebViewDisplay;
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
public class BusinessFragment extends Fragment {

    private CommonAdapter businessCommonAdapter;
    private List<CommonRowData> businessCommonDataList;
    private CommonRowData businessCommonRowData;

    private ListView businessCommonListView;
    private ProgressBarCircularIndeterminate progressBarVew;
    private List<RssItem> rssItems;;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainment_screen, container,
                false);
        setUpView(view);
        setUpAdapter();
        //new BusinessRssFeed().execute("http://www.pcworld.com/index.rss");
        new BusinessRssFeed().execute("http://theceweb.thehindu.co.in/business/?service=feeder");

        return view;
    }

    private void setUpView(View view) {
        businessCommonListView=(ListView)view.findViewById(R.id.commonListView);
        progressBarVew=(ProgressBarCircularIndeterminate)view.findViewById(R.id.progressBarCircularIndeterminate);
    }

    private void setUpAdapter()
    {

        businessCommonDataList = new ArrayList<CommonRowData>();
        businessCommonAdapter = new CommonAdapter(getActivity(), businessCommonDataList);

        int[] viewIDs = { R.id.titleTxtView,R.id.imageDisplayView ,R.id.desTxtView,R.id.time,R.id.commonLayout};
        businessCommonAdapter.setLayoutTextViews(R.layout.listviewsingleitem, viewIDs);
        businessCommonAdapter.setPopulationListener(new PopulationListener() {

            @Override
            public void populateFrom(View v, int position,
                                     final CommonRowData list, final View[] views) {

                ((TextView) views[2]).setText(list.getHead3());
                ((TextView) views[0]).setText(list.getHead1());
                ((TextView) views[3]).setText(list.getHead4());
                if(list.getHead2()!=null && !list.getHead2().equals("")) {
                    Picasso.with(getActivity()).load(list.getHead2()).into((ImageView) views[1]);
                }
                else
                    ((ImageView) views[1]).setVisibility(View.GONE);

                ((LinearLayout)views[4]).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getActivity(), WebViewDisplay.class);
                        in.putExtra("WEBURL",list.getSubHead1());
                        startActivity(in);
                    }
                });


            }

            @Override
            public void onRowCreate(View[] views) {
                // TODO Auto-generated method stub

            }
        });

        businessCommonListView.setAdapter(businessCommonAdapter);

    }



    private class BusinessRssFeed extends AsyncTask<String, Void, Void> {
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
            if(rssItems!=null) {
                progressBarVew.setVisibility(View.GONE);
                businessCommonListView.setVisibility(View.VISIBLE);
                for(RssItem rssItem : rssItems) {
                    businessCommonRowData = new CommonRowData();
                    String lefTitle = rssItem.getTitle();

                    businessCommonRowData.setHead1(lefTitle);
                    businessCommonRowData.setHead3(rssItem.getDescription());
                    businessCommonRowData.setHead2(rssItem.getImageUrl());
                    businessCommonRowData.setHead4(rssItem.getTime());
                    businessCommonRowData.setSubHead1(rssItem.getLink());

                    businessCommonAdapter.add(businessCommonRowData);
                }
                businessCommonAdapter.notifyDataSetChanged();
            }
        }
    }

}
