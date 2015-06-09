package com.hindu.news.commonrssfeeds;

import android.net.ParseException;
import android.text.Html;
import android.util.*;
import android.util.Base64;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RssReader {

    private String rssUrl;

    public RssReader(String url) {
        rssUrl = url;
    }

    public List<RssItem> getItems() throws Exception {
        List<RssItem> postDataList = new ArrayList<RssItem>();
        InputStream is = null;
        try {
            URL url = new URL(rssUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            String val = "testfeed:testfeed1";
            byte[] base = val.getBytes();
            String authorizationString = "Basic " + android.util.Base64.encodeToString(base, Base64.NO_WRAP);
            connection.setRequestProperty("Authorization", authorizationString);

            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            is = connection.getInputStream();

            // parse xml after getting the data
            XmlPullParserFactory factory = XmlPullParserFactory
                    .newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);
            boolean insideItem = false;
            int eventType = xpp.getEventType();
            RssItem pdData = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "EEE, DD MMM yyyy HH:mm:ss");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    System.out.println("get the thumb 1.." + xpp.getName() + " " + insideItem);
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                        pdData = new RssItem();
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                            pdData.setTitle(xpp.nextText()); //extract the headline
                    }
                    else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                        if (insideItem) {
                            Date postDate = dateFormat.parse(xpp.nextText());
                            String dateDisplay = dateFormat.format(postDate);
                            pdData.setTime(dateDisplay); //extract the headline
                        }
                    }else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (insideItem) {

                              String noHTMLString = Html.fromHtml(xpp.nextText()).toString();
                              pdData.setDescription(noHTMLString);
                        }//extract the headline
                    } else if (xpp.getName().equals("thumbnail") || xpp.getName().equals("image")) {

                        if (insideItem) {
                            String thumbnailUrl = xpp.getAttributeValue(null, "url");
                          //  System.out.println("get the thumb.." + thumbnailUrl);
                            pdData.setImageUrl(thumbnailUrl);
                        }
                    }
                        else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem)
                                pdData.setLink(xpp.nextText()); //extract the link of article
                        }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    postDataList.add(pdData);
                    insideItem = false;
                }

                eventType = xpp.next(); //move to next element
            }
            Log.v("tst", String.valueOf((postDataList.size())));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return postDataList;
        // return handler.getRssItemList();
    }


}
