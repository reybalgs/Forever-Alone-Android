package com.rdft.foreveralone.glue.debug;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rdft.foreveralone.R;

public class ServerAddressConfigActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server_address);

		EditText textBox = (EditText) findViewById(R.id.serverAddressInputBox);
		textBox.setText(DebugConfig.address);
	}
	
	public void onChangeButtonClick(View v) {
		EditText textBox = (EditText) findViewById(R.id.serverAddressInputBox);
		String address = textBox.getText().toString();
		DebugConfig.logDebug("ServerAddressConfig", "Address is now " + address);
		DebugConfig.address = address;
		finish();
	}
	
	public void onCancelButtonClick(View v) {
		finish();
	}
}
