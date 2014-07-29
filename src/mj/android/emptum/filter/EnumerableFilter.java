package mj.android.emptum.filter;

import android.content.Context;

public class EnumerableFilter extends Filter {
	
	public EnumerableFilter(Context ctx, String key) {
		super(ctx, key);
	}
	
	public void setValue(int value) {
		FilterStore.write(_context, getKey(), value);
	}
	
	public int getValue() {
		return FilterStore.readInteger(_context, getKey());
	}

}
