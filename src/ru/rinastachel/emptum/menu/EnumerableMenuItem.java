package ru.rinastachel.emptum.menu;

import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.filter.logic.EnumerableFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;


public class EnumerableMenuItem extends MenuItem {
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
		convertView = super.fillView(inflater, convertView);
		CheckedTextView text = (CheckedTextView)convertView.findViewById(android.R.id.text1);
		text.setChecked(isChecked());
		return convertView;
	}

	@Override
	public int getLayoutResource() {
		return R.layout.item_menu_enum;
	}
}