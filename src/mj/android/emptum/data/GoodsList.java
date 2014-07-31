package mj.android.emptum.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import android.content.Context;

public class GoodsList {
	
	private static GoodsList _instance;
	private HashMap<UUID, Item> _list;
	private Context _context;
	
	private static final String FILENAME = "emptum.data";
	
	public static GoodsList getInstance(Context ctx) {
		if (_instance == null) {
			_instance = new GoodsList(ctx);
		}
		return _instance;
	}
	
	private GoodsList(Context ctx) {
		_context = ctx;
		try {
			readData();
		} catch (Exception e) {
			_list = new HashMap<UUID, Item>();
		}		
	}

	public void add(String name) {
		UUID key = UUID.randomUUID();
		_list.put(key, new Item(name));
	}
	
	public void add(Item item) {
		UUID key = UUID.randomUUID();
		_list.put(key, item);
	}
	
	public void remove(UUID key) {
		_list.remove(key);
	}
		
	public void saveData() throws IOException {
		FileOutputStream fos = _context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(_list);
		fos.flush();
		fos.close();
	}
	
	@SuppressWarnings("unchecked")
	private void readData() throws FileNotFoundException, IOException, ClassNotFoundException  {
		FileInputStream fis = _context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		_list = (HashMap<UUID, Item>)ois.readObject();
	}
	
	public boolean deleteFile() {
		return _context.deleteFile(FILENAME);
	}
	
	public  HashMap<UUID, Item> getGoodsList() {
		return _list;
	}
	
	public Item getItem(UUID key) {
		return _list.get(key);
	}

	public void removeAll() {
		_list.clear();
	}
	
	public void removeBought() {
		for(Iterator<HashMap.Entry<UUID, Item>> it = _list.entrySet().iterator(); it.hasNext(); ) {
			HashMap.Entry<UUID, Item> entry = it.next();
			if(entry.getValue().isMarked()) {
				it.remove();
			}
		}
	}
}
