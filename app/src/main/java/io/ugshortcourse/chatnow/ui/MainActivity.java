package io.ugshortcourse.chatnow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import io.ugshortcourse.chatnow.BuildConfig;
import io.ugshortcourse.chatnow.R;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	private static final int RC_SIGN_IN = 232;
	private ViewGroup container;
	private FirebaseAuth auth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		container = findViewById(R.id.container);
		
		//Initialize firebase auth
		auth = FirebaseAuth.getInstance();
	}
	
	public void doLogin(View view) {
		if (auth.getCurrentUser() != null) {
			startActivity(new Intent(this, HomeActivity.class));
			finish();
		} else {
			//this will handle the login action
			Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
					.setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
					.enableAnonymousUsersAutoUpgrade()
					.setLogo(R.drawable.chat_logo)
					.setAvailableProviders(Arrays.asList(
							new AuthUI.IdpConfig.GoogleBuilder().build(),
							new AuthUI.IdpConfig.EmailBuilder().build(),
							new AuthUI.IdpConfig.PhoneBuilder().build(),
							new AuthUI.IdpConfig.AnonymousBuilder().build()))
					.build();
			startActivityForResult(intent, RC_SIGN_IN);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case RC_SIGN_IN:
				IdpResponse response = IdpResponse.fromResultIntent(data);
				
				// Successfully signed in
				if (resultCode == RESULT_OK) {
					startActivity(new Intent(this, HomeActivity.class));
					finish();
				} else {
					// Sign in failed
					if (response == null) {
						// User pressed back button
						showSnackbar(R.string.sign_in_cancelled);
						return;
					}
					
					if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
						showSnackbar(R.string.no_internet_connection);
						return;
					}
					
					showSnackbar(R.string.unknown_error);
					Log.e(TAG, "Sign-in error: ", response.getError());
				}
				break;
		}
	}
	
	private void showSnackbar(int resId) {
		Snackbar.make(container, getString(resId), Snackbar.LENGTH_LONG).show();
	}
}
