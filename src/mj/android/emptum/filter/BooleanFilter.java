package mj.android.emptum.filter;

import android.content.Context;

public class BooleanFilter extends Filter {
	
	public BooleanFilter(Context ctx, String id) {
		super(ctx, id);
	}
	
	public void setValue(boolean value) {
		FilterStore.write(_context, getKey(), value);
	}
	
	public boolean getValue() {
		return FilterStore.readBoolean(_context, getKey());
	}
}
