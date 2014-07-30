package mj.android.emptum.filter;

import mj.android.emptum.R;
import mj.android.emptum.filter.logic.EnumerableFilter;
import android.content.Context;

public class VisibleFilter extends EnumerableFilter {

	private static final String KEY_VISIBLE = "filter_key_visible";
	
	public static final int VISIBLE_ALL = 0;
	public static final int VISIBLE_ACTIVE = 1;
	public static final int VISIBLE_BOUGHT = 2;
	
	public VisibleFilter(Context ctx) {
		super(ctx, KEY_VISIBLE, ctx.getResources().getString(R.string.menu_group_visible), 
				ctx.getResources().getStringArray(R.array.visible_value));
	}
}