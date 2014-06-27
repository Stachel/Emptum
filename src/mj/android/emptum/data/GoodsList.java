package mj.android.emptum.data;

import java.util.ArrayList;

public class GoodsList {
	
	private static GoodsList _instance;
	private ArrayList<String> _goodsActive;
	private ArrayList<String> _goodsBought;
	
	public static GoodsList getInstance() {
		if (_instance == null) {
			_instance = new GoodsList();
		}
		return _instance;
	}
	
	private GoodsList() {
		_goodsActive = new ArrayList<String>();
		_goodsBought = new ArrayList<String>();
		//read data
		//fill arrays		
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
	
	public void saveData() {
		
	}
	
}
