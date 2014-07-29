package mj.android.emptum.fragment;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.adapter.GoodsListAdapter;
import mj.android.emptum.adapter.GoodsListAdapter.OnItemStateChangedListener;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.data.Item;
import mj.android.emptum.service.OnTouchRightDrawableListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsListFragment extends Fragment {

	private GoodsList _goodsList;
	
	private ListView _list;
	private EditText _edit;
	
	private GoodsListAdapter _adapter;
	
	private static final int REQUEST_CODE = 0;
	
	OnItemStateChangedListener _listenerStateChanged = new OnItemStateChangedListener() {
		@Override
		public void itemStateChanged(UUID id) {
			Item item = _goodsList.getItem(id);
			if (item != null) {
				item.setMarked(true);
				//_goodsList.remove(id);
				//_goodsList.addToBought(item);
				_adapter.notifyDataSetChanged();
			}
		}
	};
	
	public static GoodsListFragment newInstance() {
		GoodsListFragment fragment = new GoodsListFragment();
		return fragment;
	}

	public GoodsListFragment() {
		_goodsList = GoodsList.getInstance(getActivity());
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
	    /*super.setUserVisibleHint(isVisibleToUser);
	    if (isVisibleToUser) {
	    	if (_adapter != null) {
	    		_adapter.notifyDataSetChanged();
	    	}
	    }*/
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_active, container, false);
		_list = (ListView)rootView.findViewById(R.id.list);
		_edit = (EditText)rootView.findViewById(R.id.edit_text);
		
		_adapter = new GoodsListAdapter(getActivity());
		_adapter.setOnItemStateChangedListener(_listenerStateChanged);
		_list.setAdapter(_adapter);
				
		_edit.setOnTouchListener(new OnTouchRightDrawableListener(_edit) {
			@Override
			public boolean onDrawableTouch(MotionEvent event) {
				startVoiceRecognitionActivity();
				return true;
			}
		});
		Button btn_add = (Button)rootView.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewItem();
			}
		});

		return rootView;
	}
	
	private void addNewItem() {
		String item = _edit.getText().toString();
		_edit.setText(null);
		_goodsList.add(item);
		_adapter.notifyDataSetChanged();
	}

	private void startVoiceRecognitionActivity() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_hint));
		startActivityForResult(intent, REQUEST_CODE);
	}
 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			if (matches.size() > 0) {
				String item = matches.get(0);
				_edit.setText(item);
			} else {
				Toast.makeText(getActivity(), getString(R.string.voice_not_recognized), Toast.LENGTH_SHORT).show();
			}
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
