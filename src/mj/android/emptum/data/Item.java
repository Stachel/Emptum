package mj.android.emptum.data;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date _date;
	private String _name;
	private boolean _marked;
	
	public Item(String name) {
		_date = new Date();
		_name = name;
		_marked = false;
	}
	
	public String getName() {
		return _name;
	}
	
	public Date getDate() {
		return _date;
	}
	
	public boolean isMarked() {
		return _marked;
	}
	
	void switchMarked() { // not public!
		_marked = !_marked;
	}

	void rename(String name) { // not public!
		_name = name;
	}
}
