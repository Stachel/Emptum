package ru.rinastachel.emptum.filter;

import android.content.Context;

public class GoodsFilter {

	private static GoodsFilter _instance;

	SortFilter _sorting;
	VisibleFilter _visible;
	ShiftFilter _shift;
	
	private GoodsFilter(Context ctx) {
		_sorting = new SortFilter(ctx);
		_visible = new VisibleFilter(ctx);
		_shift = new ShiftFilter(ctx);
	}
	
	public static GoodsFilter getInstance(Context ctx) {
		if (_instance == null) {
			_instance = new GoodsFilter(ctx);
		}
		return _instance;
	}
	
	public SortFilter getSortingFilter() {
		return _sorting;
	}

	public VisibleFilter getVisibleFilter() {
		return _visible;
	}
	
	public ShiftFilter getShiftFilter() {
		return _shift;
	}
}
