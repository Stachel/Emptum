package mj.android.emptum.menu;

public interface MenuItem {
	public static final int TYPE_GROUP = 0;
	public static final int TYPE_ACTION = 1;
	public static final int TYPE_BOOLEAN = 2;
	public static final int TYPE_EBUMERABLE = 3;
	
	public String getName();
	public int getType();
}
