package mj.android.emptum.fragment.dialog;

import java.util.UUID;

import mj.android.emptum.R;
import mj.android.emptum.service.IntentKey;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class EditDialogFragment extends DialogFragment implements OnClickListener {
	
	private UUID _key;
	private String _text;
	private EditText _edit;
	
	public static EditDialogFragment getInstance(UUID key, String text) {
		EditDialogFragment fragment = new EditDialogFragment();
		Bundle args = new Bundle();
		args.putString(IntentKey.UUID, key.toString());
		args.putString(IntentKey.NAME, text);
		fragment.setArguments(args);
		
		return fragment;
	}
	
	public EditDialogFragment () {

	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		Bundle args = getArguments();
		_key = UUID.fromString(args.getString(IntentKey.UUID));
		_text = args.getString(IntentKey.NAME);

		_edit = new EditText(getActivity());
		_edit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		_edit.setText(_text);
		_edit.setSingleLine();
		builder.setView(_edit);
		builder.setTitle(R.string.dialog_edit);

		builder.setPositiveButton(R.string.dialog_btn_save, this);
		builder.setNegativeButton(R.string.dialog_btn_cancel, this);
		builder.setNeutralButton(R.string.dialog_btn_remove, this);

		return builder.create();
	  }

	@Override
	public void onCancel(DialogInterface dialog) {
		closeKeyboard();
		super.onCancel(dialog);
		sendResult(Activity.RESULT_CANCELED);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		closeKeyboard();
		int resultCode = Activity.RESULT_CANCELED;
		if (which == Dialog.BUTTON_POSITIVE) {
			String text = _edit.getText().toString().trim();
			if (text.length() == 0) {
				return;
			}
			resultCode = Activity.RESULT_OK;
		}
		if (which == Dialog.BUTTON_NEUTRAL) { // remove button
			resultCode = Activity.RESULT_FIRST_USER;
		}
		
		sendResult(resultCode);
	}
	
	private void sendResult (int resultCode) {
		
		if (getTargetFragment() == null) {
			return;
		}
		
		String name = _text;
		if (resultCode == Activity.RESULT_OK) {
			name = _edit.getText().toString().trim();
		}
		
		Intent intent = new Intent();
		intent.putExtra(IntentKey.UUID, _key.toString());
		intent.putExtra(IntentKey.NAME, name);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
	
	private void closeKeyboard() {
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(_edit.getWindowToken(), 0);
	}
}