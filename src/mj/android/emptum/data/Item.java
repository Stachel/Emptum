package mj.android.emptum.data;

import java.util.UUID;

public class Item {
	
	private UUID _id;
	private String _name;
	private boolean _marked;
	
	public Item(String name) {
		_id = UUID.randomUUID();
		_name = name;
		_marked = false;
	}
	
	public UUID getID() {
		return _id;
	}
	
	public String getName() {
		return _name;
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
