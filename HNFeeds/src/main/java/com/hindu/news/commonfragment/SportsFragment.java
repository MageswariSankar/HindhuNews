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
import com.hindu.news.HomeScreen;
import com.hindu.news.R;
import com.hindu.news.WebViewDisplay;
import com.hindu.news.commonrssfeeds.RssFeed;
import com.hindu.news.commonrssfeeds.RssItem;
import com.hindu.news.commonrssfeeds.RssReader;
import com.hindu.news.commonui.CommonAdapter;
import com.hindu.news.commonui.CommonRowData;
import com.hindu.news.commonui.PopulationListener;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mageswari on 16-05-2015.
 */
public class SportsFragment extends Fragment {
    private CommonAdapter sportsCommonAdapter;
    private List<CommonRowData> sportsCommonDataList;
    private CommonRowData sportsCommonRowData;

    private ListView sportsCommonListView;
    private ProgressBarCircularIndeterminate progressBarVew;
    private List<RssItem> rssItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainment_screen, container,
                false);
        setUpView(view);
        setUpAdapter();

        // new HomeRssFeed().execute("http://www.pcworld.com/index.rss");
        new SportsRssFeed().execute("http://theceweb.thehindu.co.in/sport/?service=feeder");

        return view;
    }

    private void setUpView(View view) {
        sportsCommonListView=(ListView)view.findViewById(R.id.commonListView);
        progressBarVew=(ProgressBarCircularIndeterminate)view.findViewById(R.id.progressBarCircularIndeterminate);

    }

    private void setUpAdapter()
    {

        sportsCommonDataList = new ArrayList<CommonRowData>();
        sportsCommonAdapter = new CommonAdapter(getActivity(), sportsCommonDataList);

        int[] viewIDs = { R.id.imageDisplay, R.id.imageDisplaySec, R.id.shortTxtView,
                R.id.shortTxtViewSec, R.id.des,R.id.desSec,R.id.time,
                R.id.timeSec ,R.id.leftLayout,R.id.rightLayout};
        sportsCommonAdapter.setLayoutTextViews(R.layout.listviewdoubleitem, viewIDs);
        sportsCommonAdapter.setPopulationListener(new PopulationListener() {

            @Override
            public void populateFrom(View v, int position,
                                     final CommonRowData list, final View[] views) {

                ((TextView) views[2]).setText(list.getHead1());
                ((TextView) views[3]).setText(list.getHead2());

                if(list.getHead3()!=null && !list.getHead3().equals(""))
                    Picasso.with(getActivity()).load(list.getHead3()).into((ImageView) views[0]);

                if(list.getHead4()!=null && !list.getHead4().equals(""))
                    Picasso.with(getActivity()).load(list.getHead4()).into((ImageView) views[1]);

                ((TextView) views[4]).setText(list.getSubHead1());
                ((TextView) views[5]).setText(list.getSubHead2());

                final String[] timeDes=list.getSubHead3().split("#");

                final String[] timeDesRight=list.getSubHead4().split("#");

                ((TextView) views[6]).setText(timeDes[0]);
                ((TextView) views[7]).setText(timeDesRight[0]);

                ((LinearLayout)views[8]).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getActivity(), WebViewDisplay.class);
                        System.out.println("Click event..."+timeDes[1]);
                        in.putExtra("WEBURL",timeDes[1]);
                        startActivity(in);
                    }
                });
                ((LinearLayout)views[9]).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getActivity(), WebViewDisplay.class);
                        in.putExtra("WEBURL", timeDesRight[1]);
                        startActivity(in);
                    }
                });

            }

            @Override
            public void onRowCreate(View[] views) {
                // TODO Auto-generated method stub

            }
        });

        sportsCommonListView.setAdapter(sportsCommonAdapter);

    }


    private class SportsRssFeed extends AsyncTask<String, Void, Void> {
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
                sportsCommonListView.setVisibility(View.VISIBLE);
                while (i < rssItems.size() - 1) {
                    sportsCommonRowData = new CommonRowData();
                    String lefTitle = rssItems.get(i).getTitle();
                    String leftImageView = rssItems.get(i).getImageUrl();
                    String descrpLeft=rssItems.get(i).getDescription();
                    String leftTime=rssItems.get(i).getTime();
                    String leftLink=rssItems.get(i).getLink();


                    i++;
                    String rightTitle = rssItems.get(i).getTitle();
                    String rightImageView = rssItems.get(i).getImageUrl();
                    String descrpRight=rssItems.get(i).getDescription();
                    String rightTime=rssItems.get(i).getTime();
                    String rightLink=rssItems.get(i).getLink();

                    sportsCommonRowData.setHead1(lefTitle);
                    sportsCommonRowData.setHead2(rightTitle);
                    sportsCommonRowData.setHead3(leftImageView);
                    sportsCommonRowData.setHead4(rightImageView);
                    sportsCommonRowData.setSubHead1(descrpLeft);
                    sportsCommonRowData.setSubHead2(descrpRight);
                    sportsCommonRowData.setSubHead3(leftTime+"#"+leftLink);
                    sportsCommonRowData.setSubHead4(rightTime+"#"+rightLink);


                    sportsCommonAdapter.add(sportsCommonRowData);
                    i++;
                }
                sportsCommonAdapter.notifyDataSetChanged();
            }
        }
    }
}
