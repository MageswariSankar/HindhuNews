package com.hindu.news.commonui;

import android.view.View;
/**
 * Created by Mageswari on 16-05-2015.
 */
public interface PopulationListener {
	
	public void populateFrom(View v, int position, CommonRowData row,
                             View[] views);

//	Call when the row is created first time. 
	public void onRowCreate(View[] views);

}
