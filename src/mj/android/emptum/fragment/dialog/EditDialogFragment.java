package mj.android.emptum.fragment.dialog;

import java.util.UUID;

import mj.android.emptum.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;

public class EditDialogFragment extends DialogFragment implements OnClickListener {
	
	private static final String ARG_KEY = "arg_key";
	private static final String ARG_TEXT = "arg_text";
	
	private UUID _key;
	private String _text;
	
	public static EditDialogFragment getInstance(UUID key, String text) {
		EditDialogFragment fragment = new EditDialogFragment();
		Bundle args = new Bundle();
		args.putString(ARG_KEY, key.toString());
		args.putString(ARG_TEXT, text);
		fragment.setArguments(args);
		
		return fragment;
	}
	
	public EditDialogFragment () {
		Bundle args = getArguments();
		_key = UUID.fromString(args.getString(ARG_KEY));
		_text = args.getString(ARG_TEXT);
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		EditText edit = new EditText(getActivity());
		edit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		edit.setText(_text);
		builder.setView(edit);
		builder.setTitle(R.string.dialog_edit);

		builder.setPositiveButton(R.string.dialog_btn_ok, this);
		builder.setNegativeButton(R.string.dialog_btn_cancel, this);
		builder.setNeutralButton(R.string.dialog_btn_remove, this);

		return builder.create();
	  }

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
			case Dialog.BUTTON_POSITIVE:
				
			// save it
			break;
		case Dialog.BUTTON_NEGATIVE:
			break;
		case Dialog.BUTTON_NEUTRAL:
			break;
		}
	}
	
	private void sendResult (int resultCode) {
		if (getTargetFragment() == null) {
			return;
		}
		Intent intent = new Intent();
		//intent.putExtra(STRING_VALUE, _value);
		//intent.putExtra(INTEGER_VALUE, _id);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}