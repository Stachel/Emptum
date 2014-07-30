package mj.android.emptum.menu;

import mj.android.emptum.filter.logic.BooleanFilter;

public class BooleanMenuItem implements MenuItem {
	private String _name;
	private BooleanFilter _filter;
	
	public BooleanMenuItem(String name, BooleanFilter filter) {
		_name = name;
		_filter = filter;
	}

	@Override
	public String getName() {
		return _name;
	}
	
	public boolean isChecked() {
		return _filter.getValue();
	}
	
	public void setChecked(boolean isChecked) {
		_filter.setValue(isChecked);
	}
	
	@Override
	public int getType() {
		return TYPE_BOOLEAN;
	}
}