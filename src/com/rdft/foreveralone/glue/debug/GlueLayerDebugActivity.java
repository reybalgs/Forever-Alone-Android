package com.rdft.foreveralone.glue.debug;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.GlueService;
import com.rdft.foreveralone.glue.GlueService.LocalBinder;
import com.rdft.foreveralone.glue.models.University;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.GlueService;
import com.rdft.foreveralone.glue.auth.LoginTask;
import com.rdft.foreveralone.glue.auth.LoginTask.ILoginReceiver;
import com.rdft.foreveralone.glue.models.*;

public class GlueLayerDebugActivity extends Activity implements ILoginReceiver {
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

	public void showConnectionError() {
		Toast toast = Toast.makeText(this,
				"Unable to connect - try again later", Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onProfileUpdateButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		University[] universities;
		UserProfile profile;
		try {
			universities = comm.getUniversities();

			if (universities.length == 0) {
				Toast.makeText(this, "Add a university first",
						Toast.LENGTH_SHORT);
				return;
			}

			profile = comm.getProfile();
			profile.university = universities[0];
			comm.updateProfile(profile);
			DebugConfig.logInfo(TAG, "Setting university to \""
					+ universities[0].name + "\"");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onYeahButtonClick(View v) {
		DebugConfig.logInfo(TAG, "YEAH!");
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		University[] unis;

		try {
			unis = comm.getUniversities();
		} catch (JSONException e) {
			e.printStackTrace();
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
		LoginTask task = new LoginTask(this);
		task.execute(null);
	}

	public void onLoginComplete(DefaultHttpClient client) {
		if (client != null) {
			this.comm = new CommHandler(client);
			Toast.makeText(this, "You are now logged in", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
		}
	}

	public void onProfilePostButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		comm.createDefaultProfile();
	}

	public void onProfileGetButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		UserProfile profile;
		try {
			profile = comm.getProfile();
		} catch (JSONException e) {
			e.printStackTrace();
			Toast.makeText(this, "Failed to retrieve profile",
					Toast.LENGTH_SHORT).show();
			return;
		}
	}

	public void onCurSchedButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		Schedule schedule;
		try {
			schedule = comm.getCurrentSchedule();
			Toast.makeText(this, "Got schedule with " + schedule.classes.size()
					+ " classes", Toast.LENGTH_LONG);
		} catch (JSONException e) {
			DebugConfig.logError(TAG, "Failed to parse current schedule JSON");
			e.printStackTrace();
		}
	}
}