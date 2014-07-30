package mj.android.emptum.filter.logic;

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
		try {
			return prefs.getInt(key, 0);
		} catch (ClassCastException e) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.remove(key);
			editor.commit();
		}
		return 0;
	}
	
	public static String readString (Context ctx, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		try {
			return prefs.getString(key, null);
		} catch (ClassCastException e) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.remove(key);
			editor.commit();
		}
		return null;
	}
	
	public static boolean readBoolean (Context ctx, String key) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		try {
			return prefs.getBoolean(key, false);
		} catch (ClassCastException e) {
			SharedPreferences.Editor editor = prefs.edit();
			editor.remove(key);
			editor.commit();
		}
		return false;		
	}
}
