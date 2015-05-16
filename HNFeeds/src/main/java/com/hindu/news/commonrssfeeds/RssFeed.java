package com.hindu.news.commonrssfeeds;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RssFeed {

	/*private String title;
	private String link;
	private String description;
	private String language;
   // private String image;
	private ArrayList<RssItem> rssItems;
	
	public RssFeed() {
        System.out.println("enter the RssFeed");
        rssItems = new ArrayList<RssItem>();
	}
	
	public RssFeed(Parcel source) {
        System.out.println("enter the RssFeed con");
		
		Bundle data = source.readBundle();
		title = data.getString("title");
		link = data.getString("link");
		description = data.getString("description");
		language = data.getString("language");
       // image=data.getString("");
		rssItems = data.getParcelableArrayList("rssItems");
		
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
        System.out.println("enter the write");
		Bundle data = new Bundle();
		data.putString("title", title);
		data.putString("link", link);
		data.putString("description", description);
		data.putString("language", language);
       // data.putString("media:thumbnailurl",image);
		data.putParcelableArrayList("rssItems", rssItems);
		dest.writeBundle(data);
	}
	
	public static final Creator<RssFeed> CREATOR = new Creator<RssFeed>() {
		public RssFeed createFromParcel(Parcel data) {
            System.out.println("enter the create");
			return new RssFeed(data);
		}
		public RssFeed[] newArray(int size) {
            System.out.println("enter the create");
            return new RssFeed[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}
	
	void addRssItem(RssItem rssItem) {
		rssItems.add(rssItem);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<RssItem> getRssItems() {
		return rssItems;
	}

	public void setRssItems(ArrayList<RssItem> rssItems) {
		this.rssItems = rssItems;
	} */

}
