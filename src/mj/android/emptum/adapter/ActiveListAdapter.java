package mj.android.emptum.adapter;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.data.Item;
import android.content.Context;
import android.view.animation.Animation;

public class ActiveListAdapter extends EmptumListAdapter {

	public ActiveListAdapter(Context ctx, ArrayList<Item> list) {
		super(ctx);
	}

	@Override
	protected int getAnimResourceID() {
		return R.anim.slide_out_right;
	}

	@Override
	protected ArrayList<Item> getGoodsList() {
		return GoodsList.getInstance(_context).getGoodsActive();
	}

	@Override
	protected Animation getAnimation(UUID id, boolean isMarked) {
		if (isMarked) {
			return getAnimForItem(id);
		}
		return null;
	}
}
