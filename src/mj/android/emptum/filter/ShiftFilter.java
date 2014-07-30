package mj.android.emptum.filter;

import android.content.Context;
import mj.android.emptum.R;
import mj.android.emptum.filter.logic.BooleanFilter;

public class ShiftFilter extends BooleanFilter {

	private static final String KEY_SHIFTING = "filter_key_shifting";
	
	public ShiftFilter(Context ctx) {
		super(ctx, KEY_SHIFTING, ctx.getResources().getString(R.string.menu_shifing));
	}
}
