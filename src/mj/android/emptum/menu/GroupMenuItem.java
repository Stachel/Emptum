package mj.android.emptum.menu;

import java.util.ArrayList;

public class GroupMenuItem implements MenuItem {
	
	private String _name;
	ArrayList<MenuItem> _childs;
	
	public GroupMenuItem(String name) {
		_name = name;
		_childs = new ArrayList<MenuItem>();
	}

	@Override
	public String getName() {
		return _name;
	}
	
	public void add(MenuItem item) {
		_childs.add(item);
	}
	
	public ArrayList<MenuItem> getChilds () {
		return _childs;
	}

	@Override
	public int getType() {
		return TYPE_GROUP;
	}
}
