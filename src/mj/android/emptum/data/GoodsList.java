package mj.android.emptum.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class GoodsList {
	
	enum ListType { 
		TYPE_NEED_TO_BUY, TYPE_ALREADY_BOUGHT 
	}
	
	private static GoodsList _instance;
	private ArrayList<Item> _goodsActive;
	private ArrayList<Item> _goodsBought;
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
			_goodsActive = new ArrayList<Item>();
			_goodsBought = new ArrayList<Item>();
		}		
	}

	public void addToActive(String name) {
		_goodsActive.add(new Item(name));
	}
	
	public void addToActive(Item item) {
		_goodsActive.add(item);
	}
	
	public void addToBought(Item item) {
		_goodsBought.add(item);
	}
	
	public void removeFromActive(UUID id) {
		for (Item item : _goodsActive) {
			if (item.getID().equals(id)) {
				_goodsActive.remove(item);
				return;
			}
		}
	}
	
	public void removeFromBought(UUID id) {
		for (Item item : _goodsBought) {
			if (item.getID().equals(id)) {
				_goodsBought.remove(item);
				return;
			}
		}
	}
	
	public void saveData() throws IOException {
		
		FileOutputStream fos = _context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(_goodsActive);
		oos.writeObject(_goodsBought);
		fos.flush();
		fos.close();
	}
	
	@SuppressWarnings("unchecked")
	private void readData() throws FileNotFoundException, IOException, ClassNotFoundException  {
		FileInputStream fis = _context.openFileInput(FILENAME);
		ObjectInputStream ois = new ObjectInputStream(fis);
		_goodsActive = (ArrayList<Item>)ois.readObject();
		_goodsBought = (ArrayList<Item>)ois.readObject();
	}
	
	public boolean deleteFile() {
		return _context.deleteFile(FILENAME);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized ArrayList<Item> getGoodsActive() {
		ArrayList<Item> list = (ArrayList<Item>) _goodsActive.clone();
		Collections.reverse(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized ArrayList<Item> getGoodsBought() {
		ArrayList<Item> list = (ArrayList<Item>) _goodsBought.clone();
		Collections.reverse(list);
		return list;
	}

	public Item getFromActive(UUID id) {
		for (Item item : _goodsActive) {
			if (item.getID().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	public Item getFromBought(UUID id) {
		for (Item item : _goodsBought) {
			if (item.getID().equals(id)) {
				return item;
			}
		}
		return null;
	}
	
	
	
}
