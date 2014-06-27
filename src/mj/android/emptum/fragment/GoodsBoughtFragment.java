package mj.android.emptum.fragment;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GoodsBoughtFragment extends Fragment {
	
	private GoodsList _goodsList;
	private ListView _list;

	public static GoodsBoughtFragment newInstance() {
		GoodsBoughtFragment fragment = new GoodsBoughtFragment();
		return fragment;
	}

	public GoodsBoughtFragment() {
		_goodsList = GoodsList.getInstance(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_bought, container, false);
		_list = (ListView)rootView.findViewById(R.id.list);
		
		_list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
				_goodsList.getGoodsBought()));
		
		return rootView;
	}
}
