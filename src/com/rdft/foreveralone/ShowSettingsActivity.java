package com.rdft.foreveralone;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import com.rdft.foreveralone.R;

public class ShowSettingsActivity extends Activity {
	@Override
	 protected void onCreate(Bundle savedInstanceState) {

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.showsettingslayout);

	  SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

	  StringBuilder builder = new StringBuilder();

	  builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));
	  builder.append("\n" + sharedPrefs.getString("updates_interval", "-1"));
	  builder.append("\n" + sharedPrefs.getString("welcome_message", "NULL"));

	  TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
	  settingsTextView.setText(builder.toString());

	 }
	
}
