package mj.android.emptum.fragment;

import java.util.ArrayList;

import mj.android.emptum.R;
import mj.android.emptum.adapter.MenuListAdapter;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.filter.GoodsFilter;
import mj.android.emptum.filter.logic.BooleanFilter;
import mj.android.emptum.filter.logic.EnumerableFilter;
import mj.android.emptum.menu.ActionMenuItem;
import mj.android.emptum.menu.ActionMenuItem.OnActionItemClickListener;
import mj.android.emptum.menu.BooleanMenuItem;
import mj.android.emptum.menu.EnumerableMenuItem;
import mj.android.emptum.menu.GroupMenuItem;
import mj.android.emptum.menu.MenuItem;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MenuListFragment extends Fragment {
	
	public interface OnSomeMenuClick {
		public void onSomeMenuClick();
	}
	
	private ArrayList<MenuItem> _menu;	
	private MenuListAdapter _adapter;
	private OnSomeMenuClick _listener;	
	
	private OnActionItemClickListener _listenerDeleteAll = new OnActionItemClickListener () {
		@Override
		public void onItemClick() {
			GoodsList.getInstance(getActivity()).removeAll();
		}
	};
	
	private OnActionItemClickListener _listenerDeleteBought = new OnActionItemClickListener () {
		@Override
		public void onItemClick() {
			GoodsList.getInstance(getActivity()).removeBought();
		}
	};
	
	public static MenuListFragment newInstance() {
		MenuListFragment fragment = new MenuListFragment();
		return fragment;
	}
	
	public MenuListFragment() {
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnSomeMenuClick) {
			_listener = (OnSomeMenuClick) activity;
		}
		generateMenu(activity);
	}
	
	private void generateMenu(Activity activity) {
		_menu = new ArrayList<>();
		
		GoodsFilter filter = GoodsFilter.getInstance(activity);
		
		addEnumerableItem(filter.getSortingFilter());
		addBooleanItem(filter.getShiftFilter());
		addEnumerableItem(filter.getVisibleFilter());
		_menu.add(new GroupMenuItem(getString(R.string.menu_group_other)));
		addActionItem(getString(R.string.menu_delete_bought), _listenerDeleteBought);
		addActionItem(getString(R.string.menu_delete_all), _listenerDeleteAll);
	}

	private void addActionItem(String title, OnActionItemClickListener listener) {
		_menu.add(new ActionMenuItem(title, listener));
	}

	private void addBooleanItem(BooleanFilter filter) {
		_menu.add(new BooleanMenuItem(filter));
	}

	private void addEnumerableItem(EnumerableFilter filter) {
		_menu.add(new GroupMenuItem(filter.getName()));
		String[] values = filter.getValues();
		for (int i = 0; i < values.length; i++) {
			_menu.add(new EnumerableMenuItem(filter, i));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
		ListView _list = (ListView) rootView.findViewById(R.id.list_menu);
		
		_adapter = new MenuListAdapter(getActivity(), _menu);
        _list.setAdapter(_adapter);
        
        _list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				_menu.get(position).onItemClick();
				_adapter.notifyDataSetChanged();
				if (_listener != null) {
					_listener.onSomeMenuClick();	
				}
			}
		});

        return rootView;
	}

}
