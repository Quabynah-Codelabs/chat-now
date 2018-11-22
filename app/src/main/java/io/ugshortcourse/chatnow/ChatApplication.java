package io.ugshortcourse.chatnow;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class ChatApplication extends Application {
	private static final String TAG = "ChatApplication";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//Initialize the Firebase Application
		FirebaseApp.initializeApp(this);
		
		// Firebase Connection Test
		Log.d(TAG, "Firebase Application " + FirebaseApp.getInstance().getName());
		
	}
}
