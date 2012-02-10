package com.rdft.foreveralone.glue.debug;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.FaHttpClient;
import com.rdft.foreveralone.glue.auth.LoginTask;
import com.rdft.foreveralone.glue.auth.LoginTask.ILoginReceiver;
import com.rdft.foreveralone.glue.models.Course;
import com.rdft.foreveralone.glue.models.DatastoreEntity;
import com.rdft.foreveralone.glue.models.Schedule;
import com.rdft.foreveralone.glue.models.Section;
import com.rdft.foreveralone.glue.models.University;
import com.rdft.foreveralone.glue.models.UserProfile;

public class GlueLayerDebugActivity extends Activity implements ILoginReceiver {
	String TAG = "GlueDebug";
	CommHandler comm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.glue_debug);

		Toast t = Toast.makeText(this, "Server address is "
				+ DebugConfig.address, Toast.LENGTH_LONG);
		t.show();
	}

	public void showConnectionError() {
		Toast toast = Toast.makeText(this,
				"Unable to connect - try again later", Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onYeahButtonClick(View v) {
		DebugConfig.logInfo(TAG, "YEAH!");
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		try {
			Schedule schedule = comm.getCurrentSchedule();
			if (schedule == null) {
				DebugConfig.logInfo(TAG,
						"You do not have a current schedule set");
				Toast.makeText(this, "You do not have a current schedule set",
						Toast.LENGTH_SHORT).show();
				return;
			}
			Course[] courses = comm.getCourses();
			if (courses.length == 0) {
				Toast.makeText(this, "Add a course first", Toast.LENGTH_SHORT)
						.show();
				return;
			}

			Section[] sections = comm.getSections();
			schedule.addClass(sections[0]);
			comm.update(schedule);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onChangeServerButtonClick(View v) {
		Intent intent = new Intent(this, ServerAddressConfigActivity.class);
		startActivity(intent);
	}

	public void onLoginButtonClick(View v) {
		LoginTask task = new LoginTask(this);
		task.execute((Void[]) null);
	}

	public void onLoginComplete(FaHttpClient client) {
		if (client != null) {
			this.comm = new CommHandler((FaHttpClient) client);
			Toast.makeText(this, "You are now logged in", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
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
			if (schedule != null) {
				Toast.makeText(this,
						"Got schedule with " + schedule.classes.size()
								+ " classes", Toast.LENGTH_LONG);
			} else {
				Toast.makeText(this, "You do not have a current schedule set.",
						Toast.LENGTH_LONG).show();
			}
		} catch (JSONException e) {
			DebugConfig.logError(TAG, "Failed to parse current schedule JSON");
			e.printStackTrace();
		}
	}

	public void onCreateProfileButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		comm.createDefaultProfile();
	}

	public void onFullTestButtonClick(View v) {
		if (comm == null) {
			Toast t = Toast.makeText(this, "Not yet logged in",
					Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		try {
			UserProfile profile = comm.getProfile();
			Course course = new Course();
			course.university = profile.getUniversity();
			DatastoreEntity entity = course;
			comm.update(entity);
			DebugConfig.logInfo(TAG,
					"Entity key after update: " + entity.getEntityKey());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onUniSearchTestButtonClick(View v) {
		try {
			University[] results = comm.searchUniversities("hello");
			if (results == null) {
				DebugConfig.logError(TAG, "results are null!");
				return;
			}

			DebugConfig.logInfo(TAG, "Got results!");
			for (University uni : results) {
				DebugConfig.logInfo(TAG, uni.name);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onProfileSearchTestButtonClick(View v) {
		try {
			UserProfile[] results = comm.searchProfiles("test");
			if (results == null) {
				DebugConfig.logError(TAG, "results are null!");
				return;
			}

			DebugConfig.logInfo(TAG, "Got results!");
			for (UserProfile profile : results) {
				DebugConfig.logInfo(TAG, profile.email);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onConnectionFailed() {
		DebugConfig.logError(TAG, "Login failed!");
	}
}