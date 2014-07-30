package mj.android.emptum.adapter;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.data.Item;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListAdapter extends BaseAdapter {
	
	ArrayList<Item> _list;
	protected Context _context;
	private OnItemStateChangedListener _listenerItemStateChanged;
			
	public interface OnItemStateChangedListener {
		public void itemStateChanged(UUID id);
	}
	
	public GoodsListAdapter (Context ctx) {
		_context = ctx;
		_list = getGoodsList();
	}
	
	public void setOnItemStateChangedListener (OnItemStateChangedListener listener) {
		_listenerItemStateChanged = listener;
	}

	protected ArrayList<Item> getGoodsList() {
		return GoodsList.getInstance(_context).getGoodsList();
	}

	@Override
	public int getCount() {
		return _list.size();
	}

	@Override
	public Object getItem(int position) {
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
		
		final Item item = _list.get(position);
		UUID id = item.getID();
		boolean isMarked = item.isMarked();
		
		title.setText(item.getName());
		icon.setBackgroundResource(getDrawable(isMarked));
		setStrikeOutText(title, isMarked);
		
		icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				item.switchMarked();
				notifyDataSetChanged();
			}
		});
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
		return isMarked ? R.drawable.check_on : R.drawable.check_off;
	}
	
	@Override
	public void notifyDataSetChanged() {
		_list = getGoodsList();
		super.notifyDataSetChanged();
	}
}
