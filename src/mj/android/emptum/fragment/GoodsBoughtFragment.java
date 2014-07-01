package mj.android.emptum.fragment;

import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.adapter.BoughtListAdapter;
import mj.android.emptum.adapter.EmptumListAdapter;
import mj.android.emptum.adapter.EmptumListAdapter.OnItemStateChangedListener;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.data.Item;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class GoodsBoughtFragment extends Fragment {

	private GoodsList _goodsList;
	
	private ListView _list;
	
	private EmptumListAdapter _adapter;
	
	OnItemStateChangedListener _listenerStateChanged = new OnItemStateChangedListener() {
		@Override
		public void itemStateChanged(UUID id) {
			Item item = _goodsList.getFromBought(id);
			if (item != null) {
				item.setMarked(false);
				_goodsList.removeFromBought(id);
				_goodsList.addToActive(item);
				_adapter.notifyDataSetChanged();
			}
		}
	};
	

	public static GoodsBoughtFragment newInstance() {
		GoodsBoughtFragment fragment = new GoodsBoughtFragment();
		return fragment;
	}

	public GoodsBoughtFragment() {
		_goodsList = GoodsList.getInstance(getActivity());
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    super.setUserVisibleHint(isVisibleToUser);
	    if (isVisibleToUser) {
	    	if (_adapter != null) {
	    		_adapter.notifyDataSetChanged();
	    	}
	    }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_bought, container, false);
		_list = (ListView)rootView.findViewById(R.id.list);
		
		_adapter = new BoughtListAdapter(getActivity(), _goodsList.getGoodsBought());
		_adapter.setOnItemStateChangedListener(_listenerStateChanged);
		_list.setAdapter(_adapter);
				
		return rootView;
	}
}