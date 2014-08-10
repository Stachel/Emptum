package ru.rinastachel.emptum.filter.logic;

import android.content.Context;

public abstract class EnumerableFilter extends Filter {
	
	private String[] _values;
	
	public EnumerableFilter(Context ctx, String key, String name, String[] values) {
		super(ctx, key, name);
		_values = values;
	}
	
	public String[] getValues() {
		return _values;
	}
	
	public void setValue(int value) {
		FilterStore.write(_context, getKey(), value);
	}
	
	public int getValue() {
		return FilterStore.readInteger(_context, getKey());
	}
}
