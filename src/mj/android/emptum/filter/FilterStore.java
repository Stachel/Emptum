package mj.android.emptum.filter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FilterStore {

	private FilterStore() {
	}

	public static void write (Context ctx, String key, Boolean value) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static void write (Context ctx, String key, String value) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void write (Context ctx, String key, Integer value) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	
	public static int readInteger (Context ctx, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		return prefs.getInt(key, 0);
	}
	
	public static String readString (Context ctx, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		return prefs.getString(key, null);
	}
	
	public static boolean readBoolean (Context ctx, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		return prefs.getBoolean(key, false);
	}
}
