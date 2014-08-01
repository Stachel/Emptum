package mj.android.emptum.menu;

import android.view.LayoutInflater;
import android.view.View;

public interface MenuItem {
	
	public int TYPE_GROUP = 0;
	public int TYPE_BOOLEAN = 1;
	public int TYPE_ENUMERABLE = 2;
	public int TYPE_ACTION = 3;
	
	public String getName();
	public int getType();
	public void onItemClick();
	public View fillView(LayoutInflater inflater, View convertView);
}
