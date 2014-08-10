package ru.rinastachel.emptum.filter.logic;

import android.content.Context;

public abstract class BooleanFilter extends Filter {
	
	public BooleanFilter(Context ctx, String id, String name) {
		super(ctx, id, name);
	}
	
	public void setValue(boolean value) {
		FilterStore.write(_context, getKey(), value);
	}

	public boolean getValue() {
		return FilterStore.readBoolean(_context, getKey());
	}
}
