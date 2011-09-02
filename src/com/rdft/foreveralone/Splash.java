package com.rdft.foreveralone;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.FaHttpClient;
import com.rdft.foreveralone.glue.auth.LoginTask;
import com.rdft.foreveralone.glue.auth.LoginTask.ILoginReceiver;
import com.rdft.foreveralone.glue.debug.DebugConfig;
import com.rdft.foreveralone.glue.debug.ServerAddressConfigActivity;
import com.rdft.foreveralone.glue.models.UserProfile;

public class Splash extends Activity implements ILoginReceiver {
	// ===========================================================
	// Fields
	// ==========================================================
	private final int SPLASH_DISPLAY_LENGTH = 2500;
	// ===========================================================
	// "Constructors"
	// ===========================================================
	/** Called when the activity is first created. */
	private int profile; // determines whether a profile is loaded
	private Intent intention; // a private intent to invoke activities

	@Override
	public void onCreate(Bundle trololo) {

		super.onCreate(trololo);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		
		LoginTask loginTask = new LoginTask(this);
		loginTask.execute(null);
	}

	@Override
	public void onLoginComplete(FaHttpClient client) {
		ProfileTask task = new ProfileTask();
		DebugConfig.comm = new CommHandler(client);
		task.execute(DebugConfig.comm);
	}
	
	public void onProfileRetrieved(UserProfile profile, boolean success) {
		if (!success) {
			DebugConfig.logError("Splash", "Failed to retrieve profile");
		}
		Intent intent = new Intent(Splash.this, Menu.class);
		Splash.this.startActivity(intent);
		Splash.this.finish();
	}

	private class ProfileTask extends AsyncTask<CommHandler, Void, UserProfile> {
		CommHandler comm;
		boolean successfullyRetrieved = false;

		@Override
		protected UserProfile doInBackground(CommHandler... params) {
			comm = params[0];

			UserProfile profile;
			try {
				profile = comm.getProfile();
				successfullyRetrieved = true;
			} catch (JSONException e) {
				successfullyRetrieved = false;
				profile = null;
			}
			return profile;
		}

		protected void onPostExecute(UserProfile result) {
			onProfileRetrieved(result, successfullyRetrieved);
		};
	}

	@Override
	public void onConnectionFailed() {
		Intent intent = new Intent(this, ServerAddressConfigActivity.class);
		this.startActivity(intent);
		this.finish();
	}
}
