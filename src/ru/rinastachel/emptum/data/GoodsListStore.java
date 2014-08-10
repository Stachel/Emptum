package ru.rinastachel.emptum.data;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

import android.content.Context;

class GoodsListStore {
	
	private static final String FILENAME = "emptum.data";

	public static void write (Context context, HashMap<UUID, Item> list) {
		FileOutputStream fos = null;
		try {
			fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			writeOutputStream(fos, list);
		} catch (FileNotFoundException e) {
			
		} finally {
			closeStream(fos);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<UUID, Item> read(Context context) {
		HashMap<UUID, Item> list = null;
		FileInputStream fis = null;
		try {
			fis = context.openFileInput(FILENAME);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fis);
				list = (HashMap<UUID, Item>)ois.readObject();
			} catch (IOException | ClassNotFoundException e2) {
				context.deleteFile(FILENAME);
			} finally {
				closeStream(ois);
			}
			
		} catch (FileNotFoundException e) {
			closeStream(fis);
		}
		return list;
	}
	
	private static void writeOutputStream(FileOutputStream fos, HashMap<UUID, Item> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			fos.flush();
		} catch (IOException e) {
		} finally {
			closeStream(oos);
		}
	}
	
	private static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}
	}
}
