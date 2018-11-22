package io.ugshortcourse.chatnow.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.ugshortcourse.chatnow.R;
import io.ugshortcourse.chatnow.ui.fragments.ChatFragment;

public class HomeActivity extends AppCompatActivity {
	
	
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {
		
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_chat:
					addFragment(new ChatFragment());
					return true;
				case R.id.navigation_profile:
					
					return true;
				case R.id.navigation_friends:
					
					return true;
				case R.id.navigation_settings:
					
					return true;
			}
			return false;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		BottomNavigationView navigation = findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		
		addFragment(new ChatFragment());
	}
	
	//Helper function to add fragment
	private void addFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
	}
	
}
