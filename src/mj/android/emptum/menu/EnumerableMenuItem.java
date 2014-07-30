package mj.android.emptum.menu;

import mj.android.emptum.filter.logic.EnumerableFilter;


public class EnumerableMenuItem implements MenuItem {
	private String _name;
	private EnumerableFilter _filter; 
	private int _id;
	
	public EnumerableMenuItem(String name, EnumerableFilter filter, int id) {
		_name = name;
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
	
	public void setChecked() {
		_filter.setValue(_id);
	}
	
	@Override
	public int getType() {
		return TYPE_EBUMERABLE;
	}
}