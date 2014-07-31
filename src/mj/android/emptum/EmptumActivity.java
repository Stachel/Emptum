package mj.android.emptum;

import mj.android.emptum.fragment.GoodsListFragment;
import mj.android.emptum.fragment.MenuListFragment;
import mj.android.emptum.fragment.MenuListFragment.OnSomeMenuClick;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

public class EmptumActivity extends Activity implements OnSomeMenuClick {

	private DrawerLayout mDrawerLayout;
	private GoodsListFragment _contentFragment;
	private MenuListFragment _menuFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emptum);

		// drawer layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
		// fragments
		_contentFragment = GoodsListFragment.newInstance();
		_menuFragment = MenuListFragment.newInstance();
		
		FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, _contentFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.menu, _menuFragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_emptum, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_filter) {
			if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
	            mDrawerLayout.closeDrawer(Gravity.RIGHT);
	        } else {
	            mDrawerLayout.openDrawer(Gravity.RIGHT);
	        }
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
			mDrawerLayout.closeDrawer(Gravity.RIGHT);
			return;
		}
		super.onBackPressed();
	}
	
	@Override
	public void onSomeMenuClick() {
		_contentFragment.notifyListChanged();
	}
}
