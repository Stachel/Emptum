package ru.rinastachel.emptum.filter;

import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.filter.logic.EnumerableFilter;
import android.content.Context;

public class SortFilter extends EnumerableFilter {

	private static final String KEY_SORTING = "filter_key_sorting";
	
	public static final int SORT_BY_ALPHABET = 0;
	public static final int SORT_BY_DATE = 1;
	
	public SortFilter(Context ctx) {
		super(ctx, KEY_SORTING, ctx.getResources().getString(R.string.menu_group_sorting), 
				ctx.getResources().getStringArray(R.array.sort_value));
	}
}
