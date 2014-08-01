package mj.android.emptum.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import mj.android.emptum.R;
import mj.android.emptum.filter.logic.EnumerableFilter;


public class EnumerableMenuItem implements MenuItem {
	private String _name;
	private EnumerableFilter _filter; 
	private int _id;
	
	public EnumerableMenuItem(EnumerableFilter filter, int id) {
		_name = filter.getValues()[id];
		_filter = filter;
		_id = id;
	}

	@Override
	public String getName() {
		return _name;
	}
	
	public boolean isChecked() {
		return _filter.getValue() == _id;
	}
	
	@Override
	public int getType() {
		return TYPE_ENUMERABLE;
	}

	@Override
	public void onItemClick() {
		_filter.setValue(_id);
	}

	@Override
	public View fillView(LayoutInflater inflater, View convertView) {
		if (convertView == null) {
			convertView = (View) inflater.inflate(R.layout.item_menu_enum, null);
		}
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		StringBuilder str = new StringBuilder();
		if (isChecked()) {
			str.append(" > ");
		}
		str.append(getName());
		text.setText(str.toString());
		
		return convertView;
	}
}