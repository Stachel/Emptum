package mj.android.emptum.menu;

import mj.android.emptum.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ActionMenuItem implements MenuItem {
	public interface OnActionItemClickListener {
		public void onItemClick();
	}
	
	private String _name;
	private OnActionItemClickListener _listener;
	
	public ActionMenuItem(String name, OnActionItemClickListener listener) {
		_name = name;
		_listener = listener;
	}

	@Override
	public String getName() {
		return _name;
	}
	
	@Override
	public int getType() {
		return TYPE_ACTION;
	}

	@Override
	public void onItemClick() {
		if (_listener != null) {
			_listener.onItemClick();
		}
	}

	@Override
	public View fillView(LayoutInflater inflater, View convertView) {
		if (convertView == null) {
			convertView = (View) inflater.inflate(R.layout.item_menu_action, null);
		}
		TextView text = (TextView)convertView.findViewById(android.R.id.text1);
		text.setText(getName());
		
		return convertView;
	}
}
