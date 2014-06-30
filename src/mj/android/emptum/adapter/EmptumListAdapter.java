package mj.android.emptum.adapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EmptumListAdapter extends BaseAdapter {
	
	private int _listType;
	private Context _context;
	
	int myProgress = 0;
	
	public EmptumListAdapter (int type, Context ctx) {
		_listType = type;
		_context = ctx;
	}
	
	private ArrayList<String> getGoodsList() {
		if (_listType == GoodsList.TYPE_ALREADY_BOUGHT) {
			return GoodsList.getInstance(_context).getGoodsBought();
		}
		return GoodsList.getInstance(_context).getGoodsActive();
	}
	
	@Override
	public int getCount() {
		return getGoodsList().size();
	}

	@Override
	public Object getItem(int position) {
		return getGoodsList().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String goods = getGoodsList().get(position);
		
		LayoutInflater inflater = LayoutInflater.from(_context);
		 
		final View view;
		if (convertView == null) {
			view = (View) inflater.inflate(R.layout.list_row, null);
		} else {
			view = convertView;
		}
		
		final CheckBox title = (CheckBox)view.findViewById(R.id.title);
		title.setText(goods);
		
		title.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				int flag = title.getPaintFlags();
				if (isChecked) {
					flag = flag | Paint.STRIKE_THRU_TEXT_FLAG;
				} else {
					flag = flag & (~Paint.STRIKE_THRU_TEXT_FLAG);
				}
				title.setPaintFlags(flag);
			}
		});
		
		Animation anim = AnimationUtils.loadAnimation(inflater.getContext(), android.R.anim.slide_out_right);
		anim.setStartOffset(1000);
		view.setAnimation(anim);
		
		return view;
	}
}
