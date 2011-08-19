package com.rdft.foreveralone.glue.debug;

import org.apache.http.client.HttpClient;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.GlueService;
import com.rdft.foreveralone.glue.GlueService.LocalBinder;
import com.rdft.foreveralone.glue.auth.AccountList;
import com.rdft.foreveralone.glue.auth.LoginManager.ILoginCallback;
import com.rdft.foreveralone.glue.models.*;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class GlueLayerDebugActivity extends Activity implements ILoginCallback {
	GlueService mService;
	boolean mBound = false;
	String TAG = "GlueDebug";
	CommHandler comm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glue_debug);
		// comm = new CommHandler();

		Toast t = Toast.makeText(this, "Server address is "
				+ DebugConfig.address, Toast.LENGTH_LONG);
		t.show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to DataService
		Intent intent = new Intent(this, GlueService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

		DebugConfig.logInfo(TAG, "POOTIS SPENCER HERE");
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}
	
	@Override
	public void onLoginComplete(HttpClient authenticatedClient) {
		comm = new CommHandler(authenticatedClient);
	}

	public void showConnectionError() {
		Toast toast = Toast.makeText(this,
				"Unable to connect - try again later", Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onNopeButtonClick(View v) {
		DebugConfig.logInfo(TAG, "NOPE");
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in", Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		Course[] courses = comm.getCourses();
		
		if (courses == null) {
			showConnectionError();
			return;
		}
		
		Toast t = Toast.makeText(this, "Successfully retrieved "
				+ courses.length + " courses",
				Toast.LENGTH_SHORT);
		t.show();
		
		for (Course course : courses) {
			DebugConfig.logInfo(TAG, course.courseCode + " - " + course.getEntityKey());
		}
	}

	public void onYeahButtonClick(View v) {
		DebugConfig.logInfo(TAG, "YEAH!");
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in", Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		University[] unis = comm.getUniversities();

		if (unis == null) {
			showConnectionError();
			return;
		}

		Toast t = Toast.makeText(this, "Successfully retrieved "
				+ new Integer(unis.length).toString() + " universities",
				Toast.LENGTH_SHORT);
		t.show();
		for (University uni : unis) {
			DebugConfig.logInfo(TAG, uni.name + " - " + uni.getEntityKey());
		}
	}
	
	public void onLoginButtonClick(View v) {
		Intent intent = new Intent(this, AccountList.class);
		startActivity(intent);
	}

	public void disconnectPeople(View v) {
		Log.i(TAG, "DISCONNECTING PEOPLE - Now with Swipe.");
		Toast t = Toast.makeText(this, "Disconnecting People. Now with Swipe.",
				Toast.LENGTH_LONG);
		t.show();
	}

	/** Defines callbacks for service binding, passed to bindService() */
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to DataService, cast the IBinder and get DataService
			// instance
			LocalBinder binder = (LocalBinder) service;
			mService = binder.getService();
			mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};
}