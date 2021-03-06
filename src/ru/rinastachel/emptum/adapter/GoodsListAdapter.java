package ru.rinastachel.emptum.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;


import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.data.GoodsList;
import ru.rinastachel.emptum.data.Item;
import ru.rinastachel.emptum.filter.GoodsFilter;
import ru.rinastachel.emptum.filter.ShiftFilter;
import ru.rinastachel.emptum.filter.SortFilter;
import ru.rinastachel.emptum.filter.VisibleFilter;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListAdapter extends BaseAdapter {
	
	private class Entry {
		private UUID _key;
		private Item _item;
		
		public Entry(HashMap.Entry<UUID, Item> entry) {
			_key = entry.getKey();
			_item = entry.getValue();
		}

		public UUID getKey() {
			return _key;
		}
		public Item getItem() {
			return _item;
		}
	}
	
	private class EntryComporator implements Comparator<Entry>, Serializable {
		
		private static final long serialVersionUID = 1L;
		private SortFilter _sortFilter;
		private ShiftFilter _shiftFilter;
		
		public EntryComporator (GoodsFilter filter) {
			_sortFilter = filter.getSortingFilter();
			_shiftFilter = filter.getShiftFilter();
		}
		
		@Override
		public int compare(Entry lhs, Entry rhs) {
			Item left = lhs.getItem();
			Item right = rhs.getItem();
			if (_shiftFilter.getValue()) {
				if (!left.isMarked() && right.isMarked())
					return -1;
				if (left.isMarked() && !right.isMarked())
					return 1;
			}
			switch (_sortFilter.getValue()) {
				case SortFilter.SORT_BY_ALPHABET:
					return left.getName().compareTo(right.getName());
				case SortFilter.SORT_BY_DATE:
					return (-1) * left.getDate().compareTo(right.getDate());
			}
			return 0;
		}
	}
	
	private ArrayList<Entry> _list;
	private Context _context;
			
	public GoodsListAdapter (Context ctx) {
		_context = ctx;
		generateGoodsList();
	}
	
	protected void generateGoodsList() {
		if (_list == null) {
			_list = new ArrayList<Entry>();
		} else {
			_list.clear();
		}
		
		HashMap<UUID, Item> map = GoodsList.getInstance(_context).getGoodsList();
		GoodsFilter filter = GoodsFilter.getInstance(_context);
		
		// get all Items according VisibleFilter
		VisibleFilter visibleFilter = filter.getVisibleFilter();
		for(Iterator<HashMap.Entry<UUID, Item>> it = map.entrySet().iterator(); it.hasNext(); ) {
			HashMap.Entry<UUID, Item> entry = it.next();
			if (matchVisibleFilter(entry.getValue(), visibleFilter)) {
				_list.add(new Entry(entry));
			}
		}
		
		EntryComporator comporator = new EntryComporator(filter);
		Collections.sort(_list, comporator);
		
		return;
	}
	
	private boolean matchVisibleFilter(Item item, VisibleFilter filter) {
		switch (filter.getValue()) {
			case VisibleFilter.VISIBLE_ACTIVE:
				return !item.isMarked();
			case VisibleFilter.VISIBLE_BOUGHT:
				return item.isMarked();
		}
		return true;
	}

	@Override
	public int getCount() {
		return _list.size();
	}

	@Override
	public Entry getItem(int position) {
		return _list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(_context);
		if (convertView == null) {
			convertView = (View) inflater.inflate(R.layout.item_goods_list, null);
		} 
		
		TextView title = (TextView)convertView.findViewById(R.id.title);
		ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
		
		Item item = _list.get(position).getItem();
		boolean isMarked = item.isMarked();
		
		title.setText(item.getName());
		icon.setBackgroundResource(getDrawable(isMarked));
		setStrikeOutText(title, isMarked);
		
		convertView.setTag(R.id.key_uuid, _list.get(position).getKey());
		convertView.setTag(R.id.key_name, _list.get(position).getItem().getName());
		
		return convertView;
	}
	
	protected void setStrikeOutText(TextView title, boolean isChecked) {
		int flag = title.getPaintFlags();
		if (isChecked) {
			flag = flag | Paint.STRIKE_THRU_TEXT_FLAG;
		} else {
			flag = flag & (~Paint.STRIKE_THRU_TEXT_FLAG);
		}
		title.setPaintFlags(flag);
	}
	
	protected int getDrawable(boolean isMarked) {
		return isMarked ? R.drawable.orangetheme_btn_check_on_disabled_holo_light : R.drawable.orangetheme_btn_check_off_disabled_focused_holo_light;
	}
	
	@Override
	public void notifyDataSetChanged() {
		generateGoodsList();
		super.notifyDataSetChanged();
	}
}
