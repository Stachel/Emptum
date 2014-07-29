package mj.android.emptum.fragment;

import java.util.ArrayList;

import mj.android.emptum.R;
import mj.android.emptum.filter.GoodsFilter;
import mj.android.emptum.menu.EnumerableMenuItem;
import mj.android.emptum.menu.GroupMenuItem;
import mj.android.emptum.menu.MenuItem;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
		
		_menu = new ArrayList<>();
		
		GoodsFilter filter = GoodsFilter.getInstance(activity);
		// add sorting
		_menu.add(new GroupMenuItem(getString(R.string.menu_group_sorting)));
		_menu.add(new EnumerableMenuItem(getString(R.string.menu_sort_alphabet), filter.getSortingFilter(), 0));
		_menu.add(new EnumerableMenuItem(getString(R.string.menu_sort_date), filter.getSortingFilter(), 1));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
		String[] mPlanetTitles = new String[] {"asdasd", "fgfdgdfg", "qweqwe"};
		ListView _list = (ListView) rootView.findViewById(R.id.list_menu);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	              R.layout.drawer_list_item, android.R.id.text1, mPlanetTitles);
        _list.setAdapter(adapter);

        return rootView;
	}

}
