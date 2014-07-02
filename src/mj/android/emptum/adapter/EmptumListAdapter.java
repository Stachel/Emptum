package mj.android.emptum.adapter;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.data.Item;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		
		TextView title = (TextView)convertView.findViewById(R.id.title);
		ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
		
		final Item item = _list.get(position);
		UUID id = item.getID();
		boolean isMarked = item.isMarked();
		
		title.setText(item.getName());
		icon.setBackgroundResource(getDrawable(isMarked));
		setStrikeOutText(title, isMarked);
		convertView.setAnimation(getAnimation(id, isMarked));
		
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
	
	protected Animation getAnimForItem (final UUID id) {
		Animation anim = AnimationUtils.loadAnimation(_context, getAnimResourceID());
		//anim.setStartOffset(500);
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
	
	
	protected int getDrawable(boolean isMarked) {
		return isMarked ? R.drawable.check_on : R.drawable.check_off;
	}
	
	@Override
	public void notifyDataSetChanged() {
		_list = getGoodsList();
		super.notifyDataSetChanged();
	}
}
