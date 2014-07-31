package mj.android.emptum.menu;

import android.view.View;
import android.widget.TextView;
import mj.android.emptum.R;
import mj.android.emptum.filter.logic.BooleanFilter;

public class BooleanMenuItem implements MenuItem {
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
	public void fillView(View convertView) {
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		StringBuilder ss = new StringBuilder();
		if (isChecked()) {
			ss.append(" > ");
		}
		ss.append(getName());
		text.setText(ss.toString()); 
	}

	@Override
	public int getLayoutResourceID() {
		return R.layout.item_menu_boolean;
	}
}