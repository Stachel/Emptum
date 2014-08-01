package mj.android.emptum.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import android.content.Context;

public class GoodsList {
	
	private static GoodsList _instance;
	private HashMap<UUID, Item> _list;
	private Context _context;
	
	public static GoodsList getInstance(Context ctx) {
		if (_instance == null) {
			_instance = new GoodsList(ctx);
		}
		return _instance;
	}
	
	private GoodsList(Context ctx) {
		_context = ctx;
		_list = readData();
		if (_list == null) {
			_list = new HashMap<UUID, Item>();
		}	
	}

	public void add(String name) {
		UUID key = UUID.randomUUID();
		_list.put(key, new Item(name));
		saveData();
	}
	
	public void add(Item item) {
		UUID key = UUID.randomUUID();
		_list.put(key, item);
		saveData();
	}
	
	public void remove(UUID key) {
		_list.remove(key);
		saveData();
	}
	
	public  HashMap<UUID, Item> getGoodsList() {
		return _list;
	}
	
	public Item getItem(UUID key) {
		return _list.get(key);
	}

	public void removeAll() {
		_list.clear();
		saveData();
	}
	
	public void removeBought() {
		for(Iterator<HashMap.Entry<UUID, Item>> it = _list.entrySet().iterator(); it.hasNext(); ) {
			HashMap.Entry<UUID, Item> entry = it.next();
			if(entry.getValue().isMarked()) {
				it.remove();
			}
		}
		saveData();
	}

	public void renameItem(UUID key, String name) {
		Item item = getItem(key);
		if (item != null) {
			item.rename(name);
		}
		saveData();
	}

	public void switchItemMarked(UUID key) {
		Item item = getItem(key);
		if (item != null) {
			item.switchMarked();
		}
		saveData();
	}

	private void saveData() {
		GoodsListStore.write(_context, _list);
	}
	
	private HashMap<UUID, Item> readData() {
		return GoodsListStore.read(_context);
	}
}
