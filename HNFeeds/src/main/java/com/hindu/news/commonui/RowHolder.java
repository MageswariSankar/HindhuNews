package com.hindu.news.commonui;

import android.view.View;
/**
 * Created by Mageswari on 16-05-2015.
 */
public class RowHolder {
	private View[] views;

	public RowHolder(final View row, final int[] viewIds) {
		if (row != null && viewIds != null) {
			views = new View[viewIds.length];
			for (int i = 0; i < viewIds.length; i++)
				views[i] = row.findViewById(viewIds[i]);
		}
	}

	public View[] getViews() {
		return views;
	}
}
