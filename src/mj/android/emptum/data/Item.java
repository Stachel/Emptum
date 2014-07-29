package mj.android.emptum.data;

import java.util.Date;
import java.util.UUID;

public class Item {
	
	private UUID _id;
	private Date _date;
	private String _name;
	private boolean _marked;
	
	public Item(String name) {
		_id = UUID.randomUUID();
		_date = new Date();
		_name = name;
		_marked = false;
	}
	
	public UUID getID() {
		return _id;
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
	
	public void setMarked(boolean marked) {
		_marked = marked;
	}

	public void switchMarked() {
		_marked = !_marked;
	}
}
