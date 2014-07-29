package mj.android.emptum.filter;

import android.content.Context;

public class GoodsFilter {

	private static GoodsFilter _instance;
	
	private static final String KEY_SORTING = "filter_key_sorting";
	private static final String KEY_FILTERING = "filter_key_filtering";
	private static final String KEY_SHIFTING = "filter_key_shifting";
	
	EnumerableFilter _sorting;
	EnumerableFilter _filtering;
	BooleanFilter _shifting;
	
	private GoodsFilter(Context ctx) {
		_sorting = new EnumerableFilter(ctx, KEY_SORTING);
		_filtering = new EnumerableFilter(ctx, KEY_FILTERING);
		_shifting = new BooleanFilter(ctx, KEY_SHIFTING);
	}
	
	public static GoodsFilter getInstance(Context ctx) {
		if (_instance == null) {
			_instance = new GoodsFilter(ctx);
		}
		return _instance;
	}
	
	public EnumerableFilter getSortingFilter() {
		return _sorting;
	}

	public EnumerableFilter getFilteringFilter() {
		return _filtering;
	}
	
	public BooleanFilter getShiftingFilter() {
		return _shifting;
	}
}
