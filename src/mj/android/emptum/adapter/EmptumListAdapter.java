package mj.android.emptum.adapter;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.data.Item;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public abstract class EmptumListAdapter extends BaseAdapter {
	
	ArrayList<Item> _list;
	protected Context _context;
	private OnItemStateChangedListener _listenerItemStateChanged;
			
	public interface OnItemStateChangedListener {
		public void itemStateChanged(UUID id);
	}
	
	public EmptumListAdapter (Context ctx) {
		_context = ctx;
		_list = getGoodsList();
	}
	
	public void setOnItemStateChangedListener (OnItemStateChangedListener listener) {
		_listenerItemStateChanged = listener;
	}

	abstract protected int getAnimResourceID();
	abstract protected Animation getAnimation(UUID id, boolean isMarked);
	abstract protected ArrayList<Item> getGoodsList();

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
			convertView = (View) inflater.inflate(R.layout.list_row, null);
		} 
		
		CheckBox title = (CheckBox)convertView.findViewById(R.id.title);
		
		final Item item = _list.get(position);
		UUID id = item.getID();
		boolean isMarked = item.isMarked();
		
		Log.e("MY", "item " + position + ": " + id + " - " + item.getName() + " - " + isMarked);
		
		title.setText(item.getName());
		title.setChecked(isMarked);
		setStrikeOutText(title, isMarked);
		convertView.setAnimation(getAnimation(id, isMarked));
		
		title.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.switchMarked();
				notifyDataSetChanged();
			}
		});
		return convertView;
	}
	
	protected void setStrikeOutText(CheckBox title, boolean isChecked) {
		int flag = title.getPaintFlags();
		if (isChecked) {
			flag = flag | Paint.STRIKE_THRU_TEXT_FLAG;
		} else {
			flag = flag & (~Paint.STRIKE_THRU_TEXT_FLAG);
		}
		title.setPaintFlags(flag);
	}
	
	protected Animation getAnimForItem (final UUID id) {
		Animation anim = AnimationUtils.loadAnimation(_context, getAnimResourceID());
		anim.setStartOffset(1000);
		anim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if (_listenerItemStateChanged != null) {
					_listenerItemStateChanged.itemStateChanged(id);
				}
			}
		});
		return anim;
	}
	
	@Override
	public void notifyDataSetChanged() {
		_list = getGoodsList();
		super.notifyDataSetChanged();
	}
}
