package mj.android.emptum.adapter;

import java.util.ArrayList;

import mj.android.emptum.R;
import mj.android.emptum.menu.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuListAdapter extends BaseAdapter {
	
	private Context _ctx;
	private ArrayList<MenuItem> _items;

	public MenuListAdapter(Context ctx, ArrayList<MenuItem> items) {
		_ctx = ctx;
		_items = items;
	}

	@Override
	public int getCount() {
		return _items.size();
	}
	
	@Override
	public int getViewTypeCount() {
		return 4;
	}
	
	@Override
	public int getItemViewType(int position) {
		return _items.get(position).getType();
	}

	@Override
	public MenuItem getItem(int position) {
		return _items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(_ctx);
		if (convertView == null) {
			convertView = (View) inflater.inflate(R.layout.list_row, null);
		} 
		
		/*
		 String notSet = getString(R.string.not_set);
			
			Filter filter = getItem(position);
			Object value = filter.getValue();
			
			//TODO maybe some inherited methods?
			switch (getItemViewType(position)) {
				case Filter.AttributeFilter: 
					if (convertView == null) {
						convertView = _inflater.inflate(R.layout.row_filter_attribute, null);
					}
					TextView attrHeader = (TextView) convertView.findViewById(R.id.attr_header);
		    		TextView attrTitle = (TextView) convertView.findViewById(R.id.attr_value1);
		    		TextView attrValue = (TextView) convertView.findViewById(R.id.attr_value2);
		    		
		    		attrHeader.setText(filter.getName());
		    				    		
		    		IValue a = ((AttributeFilter)filter).getAttribute();
					IValue av = ((AttributeFilter)filter).getAttributeValue();
					if (a == null) {
						attrTitle.setText(notSet);
						attrValue.setText("");
					} else {
						attrTitle.setText(a.name());
						attrValue.setText(av == null ? notSet : av.name());
					}
					break;
					
				case Filter.BooleanFilter: 
					if (convertView == null) {
						convertView = _inflater.inflate(R.layout.row_filter_boolean, null);
					}
					CheckedTextView checkBox = (CheckedTextView) convertView.findViewById(R.id.boolean_header);
					checkBox.setText(filter.getName());
	    			checkBox.setChecked(((BooleanFilter)filter).getValue());
					break;
					
				case Filter.DateFilter:  
					if (convertView == null) {
						convertView = _inflater.inflate(R.layout.row_filter_date, null);
					}
					TextView dateHeader = (TextView) convertView.findViewById(R.id.date_header);
		    		TextView dateValue = (TextView) convertView.findViewById(R.id.date_value);
		    		dateHeader.setText(filter.getName());
		    		dateValue.setText(value == null ? notSet : ToString.date((Date)value));
					break;
					
				case Filter.DatePeriodFilter:  
					if (convertView == null) {
						convertView = _inflater.inflate(R.layout.row_filter_date_period, null);
					}
					TextView periodHeader = (TextView) convertView.findViewById(R.id.period_header);
		    		TextView periodValue = (TextView) convertView.findViewById(R.id.period_value);
		    		periodHeader.setText(filter.getName());
		    		Pair<Date, Date> dates = (Pair<Date, Date>)value;
		    		String valString = notSet;
					if (dates.first != null && dates.second != null) {
						valString = getString(R.string.dates_period, ToString.date(dates.first), ToString.date(dates.second));
					}
					periodValue.setText(valString);
					break;
					
				case Filter.EnumerableFilter:  
					if (convertView == null) {
						convertView = _inflater.inflate(R.layout.row_filter_enumerable, null);
					}
					TextView enumHeader = (TextView) convertView.findViewById(R.id.enum_header);
		    		TextView enumValue = (TextView) convertView.findViewById(R.id.enum_value);
		    		enumHeader.setText(filter.getName());
		    		enumValue.setText(value == null ? notSet : value.toString());
					break;
			}
			return convertView;
		 */
		return null;
	}

}
