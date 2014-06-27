package mj.android.emptum.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.content.Context;

public class GoodsList {
	
	private static GoodsList _instance;
	private ArrayList<String> _goodsActive;
	private ArrayList<String> _goodsBought;
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
			_goodsActive = new ArrayList<String>();
			_goodsBought = new ArrayList<String>();
		}		
	}

	public void addToActive(String item) {
		_goodsActive.add(item);
	}
	
	public void addToBought(String item) {
		_goodsBought.add(item);
	}
	
	public void removeFromActive(int position) {
		_goodsActive.remove(position);
	}
	
	public void removeFromBought(int position) {
		_goodsBought.remove(position); 
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
		_goodsActive = (ArrayList<String>)ois.readObject();
		_goodsBought = (ArrayList<String>)ois.readObject();
	}
	
	public boolean deleteFile() {
		return _context.deleteFile(FILENAME);
	}
	
	public ArrayList<String> getGoodsActive() {
		return _goodsActive;
	}
	
	public ArrayList<String> getGoodsBought() {
		return _goodsBought;
	}
}
