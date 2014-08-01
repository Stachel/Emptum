package mj.android.emptum.adapter;

import java.util.ArrayList;

import mj.android.emptum.menu.GroupMenuItem;
import mj.android.emptum.menu.MenuItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuListAdapter extends BaseAdapter {
	
	private Context _context;
	private ArrayList<MenuItem> _items;

	public MenuListAdapter(Context ctx, ArrayList<MenuItem> items) {
		_context = ctx;
		_items = items;
	}

	@Override
	public int getCount() {
		return _items.size();
	}
	
	@Override
	public int getViewTypeCount() {
		return 4;
	}
	
	@Override
	public int getItemViewType(int position) {
		return _items.get(position).getType();
	}

	@Override
	public MenuItem getItem(int position) {
		return _items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public boolean isEnabled(int position) {
		return !(_items.get(position) instanceof GroupMenuItem);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(_context);
		return getItem(position).fillView(inflater, convertView);
	}
}
