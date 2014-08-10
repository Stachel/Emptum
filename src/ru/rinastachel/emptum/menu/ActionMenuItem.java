package ru.rinastachel.emptum.menu;

import ru.rinastachel.emptum.R;

public class ActionMenuItem extends MenuItem {
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
	public int getLayoutResource() {
		return R.layout.item_menu_action;
	}
}
