package mj.android.emptum.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class GoodsList {
	
	private static GoodsList _instance;
	private ArrayList<Item> _list;
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
			_list = new ArrayList<Item>();
		}		
	}

	public void add(String name) {
		_list.add(new Item(name));
	}
	
	public void add(Item item) {
		_list.add(item);
	}
	
	public void remove(UUID id) {
		Item item = getItem(id);
		if (item != null) {
			_list.remove(item);
		}
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
		_list = (ArrayList<Item>)ois.readObject();
	}
	
	public boolean deleteFile() {
		return _context.deleteFile(FILENAME);
	}
	
	//@SuppressWarnings("unchecked")
	//synchronized
	public  ArrayList<Item> getGoodsList() {
		//ArrayList<Item> list = (ArrayList<Item>) _list.clone();
		//Collections.reverse(list);
		//return list;
		return _list;
	}
	
	public Item getItem(UUID id) {
		for (Item item : _list) {
			if (item.getID().equals(id)) {
				return item;
			}
		}
		return null;
	}
}
