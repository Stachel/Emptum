package ru.rinastachel.emptum.filter;

import android.content.Context;
import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.filter.logic.BooleanFilter;

public class ShiftFilter extends BooleanFilter {

	private static final String KEY_SHIFTING = "filter_key_shifting";
	
	public ShiftFilter(Context ctx) {
		super(ctx, KEY_SHIFTING, ctx.getResources().getString(R.string.menu_shifing));
	}
}
