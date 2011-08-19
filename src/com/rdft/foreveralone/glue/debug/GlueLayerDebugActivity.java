package com.rdft.foreveralone.glue.debug;

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

	public void onNopeButtonClick(View v) {
		DebugConfig.logInfo(TAG, "NOPE");
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		Course[] courses = comm.getCourses();

		if (courses == null) {
			showConnectionError();
			return;
		}

		Toast t = Toast.makeText(this, "Successfully retrieved "
				+ courses.length + " courses", Toast.LENGTH_SHORT);
		t.show();

		for (Course course : courses) {
			DebugConfig.logInfo(TAG,
					course.courseCode + " - " + course.getEntityKey());
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
		LoginTask task = new LoginTask(this);
		task.execute(null);
	}

	@Override
	public void onLoginComplete(DefaultHttpClient client) {
		this.comm = new CommHandler(client);
	}

	public void onProfileButtonClick(View v) {
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
}