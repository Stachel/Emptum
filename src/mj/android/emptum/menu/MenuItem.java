package mj.android.emptum.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public abstract class MenuItem {
	
	public int TYPE_GROUP = 0;
	public int TYPE_BOOLEAN = 1;
	public int TYPE_ENUMERABLE = 2;
	public int TYPE_ACTION = 3;
	
	public abstract String getName();
	public abstract int getType();
	public abstract void onItemClick();
	public abstract int getLayoutResource();
	
	public View fillView(LayoutInflater inflater, View convertView) {
		if (convertView == null) {
			convertView = (View) inflater.inflate(getLayoutResource(), null);
		}
		
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		text.setText(getName());
		
		return convertView;
	}
}
