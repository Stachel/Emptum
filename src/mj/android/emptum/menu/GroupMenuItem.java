package mj.android.emptum.menu;

import java.util.ArrayList;

import mj.android.emptum.R;

import android.view.View;
import android.widget.TextView;

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

	@Override
	public void onItemClick() {
		return;
	}

	@Override
	public void fillView(View convertView) {
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		text.setText(getName());
	}

	@Override
	public int getLayoutResourceID() {
		return R.layout.item_menu_group;
	}
}
