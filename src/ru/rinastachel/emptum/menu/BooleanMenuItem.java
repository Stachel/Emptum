package ru.rinastachel.emptum.menu;

import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.filter.logic.BooleanFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;

public class BooleanMenuItem extends MenuItem {
	private String _name;
	private BooleanFilter _filter;
	
	public BooleanMenuItem(BooleanFilter filter) {
		_name = filter.getName();
		_filter = filter;
	}

	@Override
	public String getName() {
		return _name;
	}
	
	public boolean isChecked() {
		return _filter.getValue();
	}
	
	@Override
	public int getType() {
		return TYPE_BOOLEAN;
	}

	@Override
	public void onItemClick() {
		_filter.setValue(!isChecked());
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
		return R.layout.item_menu_boolean;
	}
}