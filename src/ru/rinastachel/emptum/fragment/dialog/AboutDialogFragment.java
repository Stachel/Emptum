package ru.rinastachel.emptum.fragment.dialog;

import ru.rinastachel.emptum.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class AboutDialogFragment  extends DialogFragment {

	public static AboutDialogFragment getInstance() {
		AboutDialogFragment fragment = new AboutDialogFragment();
		return fragment;
	}
	
	public AboutDialogFragment () {
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View v = inflater.inflate(R.layout.dialog_about, null);
		TextView dialogTitle = (TextView)v.findViewById(R.id.dialogTitle);
		TextView version = (TextView)v.findViewById(R.id.version);
		TextView mail = (TextView)v.findViewById(R.id.mail);
		
		dialogTitle.setText(R.string.menu_about);
		
		String ver = getAppVersion(); 
		if (ver != null) {
			version.setText(getString(R.string.dialog_about_version, ver));
		} else {
			version.setVisibility(View.GONE);
		}
		mail.setText(R.string.dialog_about_mail);

		builder.setView(v);

		builder.setPositiveButton(R.string.dialog_btn_ok, null);
		
		return builder.create();
	}
	
	private String getAppVersion () {
		try {
			PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
			return pInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
