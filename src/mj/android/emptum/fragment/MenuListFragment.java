package mj.android.emptum.fragment;

import java.util.ArrayList;

import mj.android.emptum.R;
import mj.android.emptum.adapter.MenuListAdapter;
import mj.android.emptum.filter.GoodsFilter;
import mj.android.emptum.filter.logic.BooleanFilter;
import mj.android.emptum.filter.logic.EnumerableFilter;
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
import android.widget.ListView;

public class MenuListFragment extends Fragment {
	
	private ArrayList<MenuItem> _menu;	
	
	public static MenuListFragment newInstance() {
		MenuListFragment fragment = new MenuListFragment();
		return fragment;
	}
	
	public MenuListFragment() {
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		generateMenu(activity);
	}
	
	private void generateMenu(Activity activity) {
		_menu = new ArrayList<>();
		
		GoodsFilter filter = GoodsFilter.getInstance(activity);
		
		addEnumFilter(filter.getSortingFilter());
		addEnumFilter(filter.getVisibleFilter());
		_menu.add(new GroupMenuItem(getString(R.string.menu_group_other)));
		addBooleanFilter(filter.getShiftFilter());
	}

	private void addBooleanFilter(BooleanFilter filter) {
		_menu.add(new BooleanMenuItem(filter.getName(), filter));
	}

	private void addEnumFilter(EnumerableFilter filter) {
		_menu.add(new GroupMenuItem(filter.getName()));
		String[] values = filter.getValues();
		for (int i = 0; i < values.length; i++) {
			_menu.add(new EnumerableMenuItem(values[i], filter, i));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
		ListView _list = (ListView) rootView.findViewById(R.id.list_menu);
		
		MenuListAdapter adapter = new MenuListAdapter(getActivity(), _menu);
        _list.setAdapter(adapter);

        return rootView;
	}

}
