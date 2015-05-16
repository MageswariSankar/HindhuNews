package com.hindu.news.commonui;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * Created by Mageswari on 16-05-2015.
 */
public class CommonAdapter extends BaseAdapter {

	private List<CommonRowData> elements;
	private Context mContext;

	private int layoutID = 0;
	private int[] viewIds;
	private int lastSelectedPosition;
	private int selectedRowColor = -1;
	private int unSelectedRowColor = Color.parseColor("#00000000");	
	private int selectedResourceRowColor = -1;

	private PopulationListener listener;

	public CommonAdapter(Context context, List<CommonRowData> elem) {
		this.mContext = context;
		this.elements = elem;

	}

	public void setLayoutTextViews(int layoutID, int[] viewIDs) {
		this.layoutID = layoutID;
		this.viewIds = viewIDs;
	}

	public void clear() {
		elements.clear();
	}

	public void add(CommonRowData row) {
		elements.add(row);
	}
	
	public void add(int position, CommonRowData row) {
		elements.add(position, row);
	}
	
	public void addAll(List<CommonRowData> rows) {
		elements.addAll(rows);
	}

	public void remove(int position) {
		elements.remove(position);
	}

	public int getCount() {
		return elements.size();
	}

	public Object getItem(int position) {
		if (getCount() > 0)
			return elements.get(position);
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public void setPopulationListener(PopulationListener listener) {
		this.listener = listener;
	}

	public View getView(int position, View row, ViewGroup parent) {
		RowHolder holder = null;

		if (row == null) {

			row = (View) LayoutInflater.from(mContext).inflate(layoutID,
					parent, false);

			holder = new RowHolder(row, viewIds);
			listener.onRowCreate(holder.getViews());
			row.setTag(holder);

		} else {
			holder = (RowHolder) row.getTag();
		}
		
		if (row != null) {
			if (selectedRowColor != -1) {
				if (lastSelectedPosition == position) {
					// Selected Row Color
					row.setBackgroundColor(selectedRowColor);
				} else {
					// UnSelected Row Color
					row.setBackgroundColor(unSelectedRowColor);
				}
			}

			if (selectedResourceRowColor != -1) {
				if (lastSelectedPosition == position) {
					// Selected Row Color
					row.setBackgroundResource(selectedResourceRowColor);
				} else {
					// UnSelected Row Color
					row.setBackgroundColor(unSelectedRowColor);
				}
			}

		}

		/**
		 * Here we store the position value with key as layoutID
		 * to handle alternate colors for list rows.  
		 * 
		 * please refer OnItemClickListener (onListClick in watch list) for listView.
		 * 
		 */
		row.setTag(layoutID, position);

		listener.populateFrom(row, position, elements.get(position),
				holder.getViews());

		return (row);
	}

	public void setLastSelectedPosition(int position) {
        lastSelectedPosition = position;
	}
	
	public void setRowColor(int selectedRowColor) {
        this.selectedRowColor = selectedRowColor;
	}
	
	public void setUnSelectedRowColor(int unSelectedRowColor) {
        this.unSelectedRowColor = unSelectedRowColor;
	}
	
	public void setResourceRowColor(int selectedResourceRowColor) {
        this.selectedResourceRowColor = selectedResourceRowColor;
	}

}
