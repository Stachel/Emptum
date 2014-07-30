package mj.android.emptum.filter.logic;

import android.content.Context;

public abstract class Filter {
	private String _key;
	private String _name;
	protected Context _context;
	
	public Filter (Context ctx, String key, String name) {
		_key = key;
		_context = ctx;
		_name = name;
	}
	
	public String getKey() {
		return _key;
	}
	
	public String getName() {
		return _name;
	}
}
