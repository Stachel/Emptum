package mj.android.emptum.fragment;

import java.util.ArrayList;
import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.adapter.GoodsListAdapter;
import mj.android.emptum.data.GoodsList;
import mj.android.emptum.fragment.dialog.ConfirmItemDialogFragment;
import mj.android.emptum.fragment.dialog.EditDialogFragment;
import mj.android.emptum.service.IntentKey;
import mj.android.emptum.service.OnTouchRightDrawableListener;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsListFragment extends Fragment {

	private GoodsList _goodsList;
	
	private ListView _list;
	private EditText _edit;
	
	private GoodsListAdapter _adapter;
	
	private static final int REQUEST_VOICE_CODE = 0;
	private static final int REQUEST_EDIT_CODE = 1;
	private static final int REQUEST_REMOVE_CODE = 2;
	
	public static GoodsListFragment newInstance() {
		GoodsListFragment fragment = new GoodsListFragment();
		return fragment;
	}

	public GoodsListFragment() {
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		_goodsList = GoodsList.getInstance(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods, container, false);
		_list = (ListView)rootView.findViewById(R.id.list);
		_edit = (EditText)rootView.findViewById(R.id.edit_text);
		
		_adapter = new GoodsListAdapter(getActivity());
		_list.setAdapter(_adapter);
		
		_list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				UUID key = (UUID) view.getTag(R.id.key_uuid);
				String name = (String) view.getTag(R.id.key_name);
				EditDialogFragment editFragment = EditDialogFragment.getInstance(key, name);
				editFragment.setTargetFragment(GoodsListFragment.this, REQUEST_EDIT_CODE);
				editFragment.show(getFragmentManager(), "EditDialogFragment");
				return true;
			}
		});
		
		_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				UUID key = (UUID) view.getTag(R.id.key_uuid);
				_goodsList.switchItemMarked(key);
				_adapter.notifyDataSetChanged();
			}
		});
		
		_edit.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					addNewItem();
					return true;
				}
				return false;
			}
		});
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
		if (item == null || item.trim().length() == 0) {
			return;
		}
		_edit.setText(null);
		_goodsList.add(item.trim());
		_adapter.notifyDataSetChanged();
	}

	private void startVoiceRecognitionActivity() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.voice_hint));
		startActivityForResult(intent, REQUEST_VOICE_CODE);
	}
 
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case REQUEST_VOICE_CODE:
				if (resultCode == Activity.RESULT_OK) {
					ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					if (matches.size() > 0) {
						String item = matches.get(0);
						_edit.setText(item);
					} else {
						Toast.makeText(getActivity(), getString(R.string.voice_not_recognized), Toast.LENGTH_SHORT).show();
					}
				}
				break;
				
			case REQUEST_EDIT_CODE:
				UUID key = UUID.fromString(data.getStringExtra(IntentKey.UUID));
				String name = data.getStringExtra(IntentKey.NAME);
				if (resultCode == Activity.RESULT_OK) {
					_goodsList.renameItem(key, name);
					_adapter.notifyDataSetChanged();
				}		
				if (resultCode == Activity.RESULT_FIRST_USER) {
					String message = getString(R.string.dialog_confirm_remove_item, name);
					ConfirmItemDialogFragment confirmFragment = ConfirmItemDialogFragment.getInstance(message, key);
					confirmFragment.setTargetFragment(GoodsListFragment.this, REQUEST_REMOVE_CODE);
					confirmFragment.show(getFragmentManager(), "ConfirmItemDialogFragment");
				}
				break;
			
			case REQUEST_REMOVE_CODE:
				if (resultCode == Activity.RESULT_OK) {
					UUID k = UUID.fromString(data.getStringExtra(IntentKey.UUID));
					_goodsList.remove(k);
					_adapter.notifyDataSetChanged();
				}
				break;
		}

        super.onActivityResult(requestCode, resultCode, data);
    }
	
	public void notifyListChanged() {
		_adapter.notifyDataSetChanged();
	}
}
