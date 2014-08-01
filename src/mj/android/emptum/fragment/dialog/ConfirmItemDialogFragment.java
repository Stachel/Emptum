package mj.android.emptum.fragment.dialog;

import java.util.UUID;

import mj.android.emptum.service.IntentKey;

import android.content.Intent;
import android.os.Bundle;

public class ConfirmItemDialogFragment extends ConfirmDialogFragment {
	
	public static ConfirmItemDialogFragment getInstance(String message, UUID key) {
		ConfirmItemDialogFragment fragment = new ConfirmItemDialogFragment();
		Bundle args = createArguments(message);
		args.putString(IntentKey.UUID, key.toString());
		fragment.setArguments(args);
			
		return fragment;
	}
		
	public ConfirmItemDialogFragment () {
	}
	
	
	@Override
	protected Intent createIntent() {
		Intent intent = new Intent();
		String uuid = (String) getArguments().get(IntentKey.UUID);
		intent.putExtra(IntentKey.UUID, uuid);
		return intent;
	}
	
}