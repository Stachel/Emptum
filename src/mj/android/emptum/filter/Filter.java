package mj.android.emptum.filter;

import android.content.Context;

public abstract class Filter {
	private String _key;
	protected Context _context;
	
	public Filter (Context ctx, String key) {
		_key = key;
		_context = ctx;
	}
	
	public String getKey() {
		return _key;
	}
}
