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
        new BusinessRssFeed().execute("http://www.pcworld.com/index.rss");

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

        int[] viewIDs = { R.id.titleTxtView,R.id.imageDisplayView };
        businessCommonAdapter.setLayoutTextViews(R.layout.listviewsingleitem, viewIDs);
        businessCommonAdapter.setPopulationListener(new PopulationListener() {

            @Override
            public void populateFrom(View v, int position,
                                     final CommonRowData list, final View[] views) {

                ((TextView) views[0]).setText(list.getHead1());
                Picasso.with(getActivity()).load(list.getHead2()).into((ImageView) views[1]);

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
                    businessCommonRowData.setHead2(rssItem.getImageUrl());

                    businessCommonAdapter.add(businessCommonRowData);
                }
                businessCommonAdapter.notifyDataSetChanged();
            }
        }
    }

}
