package mj.android.emptum.fragment;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GoodsBoughtFragment extends Fragment {
	
	private GoodsList _goodsList;

	public static GoodsBoughtFragment newInstance() {
		GoodsBoughtFragment fragment = new GoodsBoughtFragment();
		return fragment;
	}

	public GoodsBoughtFragment() {
		_goodsList = GoodsList.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_bought, container, false);

		return rootView;
	}
}
