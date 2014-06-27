package mj.android.emptum.fragment;

import java.util.ArrayList;

import mj.android.emptum.R;
import mj.android.emptum.data.GoodsList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class GoodsActiveFragment extends Fragment {

	private GoodsList _goodsList;
	
	private ListView _list;
	private EditText _edit;
	
	private static final int REQUEST_CODE = 0;

	public static GoodsActiveFragment newInstance() {
		GoodsActiveFragment fragment = new GoodsActiveFragment();
		return fragment;
	}

	public GoodsActiveFragment() {
		_goodsList = GoodsList.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_active, container, false);
		_list = (ListView)rootView.findViewById(R.id.list);
		_edit = (EditText)rootView.findViewById(R.id.edit_text);
		
		Button btn_add = (Button)rootView.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewItem();
			}
		});
		
		Button btn_voice = (Button)rootView.findViewById(R.id.btn_voice);
		btn_voice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startVoiceRecognitionActivity();
			}
		});
		return rootView;
	}
	
	private void addNewItem() {
		String item = _edit.getText().toString();
		_goodsList.addToActive(item);
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
			_list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                    matches));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
