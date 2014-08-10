package ru.rinastachel.emptum.fragment.dialog;

import ru.rinastachel.emptum.R;
import ru.rinastachel.emptum.service.IntentKey;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class ConfirmDialogFragment extends DialogFragment implements OnClickListener {
		
	public static ConfirmDialogFragment getInstance(String message) {
		ConfirmDialogFragment fragment = new ConfirmDialogFragment();
		fragment.setArguments(createArguments(message));
		return fragment;
	}
	
	protected static Bundle createArguments(String message) {
		Bundle args = new Bundle();
		args.putString(IntentKey.MESSAGE, message);
		return args;
	}
		
	public ConfirmDialogFragment () {
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			
		Bundle args = getArguments();
		String message = args.getString(IntentKey.MESSAGE);

		builder.setMessage(message);

		builder.setPositiveButton(R.string.dialog_btn_remove, this);
		builder.setNegativeButton(R.string.dialog_btn_cancel, this);
		
		return builder.create();
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		sendResult(Activity.RESULT_CANCELED);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		int resultCode = which == Dialog.BUTTON_POSITIVE ? Activity.RESULT_OK : Activity.RESULT_CANCELED;
		sendResult(resultCode);		
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() != null) {
			getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, createIntent());
		}
	}

	protected Intent createIntent() {
		return null;
	}
	
}
