package mj.android.emptum.menu;

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
}
