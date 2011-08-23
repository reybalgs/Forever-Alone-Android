package com.rdft.foreveralone;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AddSchedule extends Activity {
	/* This is the class that handles the creation of
	 * schedules.
	 */
	private Intent intention; // intent for invoking
	/*
	private TextView mTimeDisplay;
	
	private int mHour;
	private int mMinute;
	
	static final int TIME_DIALOG_ID = 0;
	*/
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addschedule);
		/*
		// Set time view
		mTimeDisplay = (TextView) findViewById(R.id.timeprev);
		*/
		// Button's listener
		final Button addTimeButton = (Button)findViewById(R.id.settime);
		addTimeButton.setOnClickListener(new OnClickListener() 
		{
					
			public void onClick(View v) 
			{
				// Pat change this button to whatever you need
				finish();
			}
		});
		
		final Button submitSchedButton = (Button)findViewById(R.id.scheddone);
		submitSchedButton.setOnClickListener(new OnClickListener() 
		{
					
			public void onClick(View v) 
			{
				// Pat change this button to whatever you need
				finish();
			}
		});
		/*
		// Get the time
		final Calendar c = Calendar.getInstance();
	    mHour = c.get(Calendar.HOUR_OF_DAY);
	    mMinute = c.get(Calendar.MINUTE);
	    */
	    
	    // display the current date
        //updateDisplay();
	}
	/*
	private void updateDisplay() {
	    mTimeDisplay.setText(
	        new StringBuilder()
	                .append(pad(mHour)).append(":")
	                .append(pad(mMinute)));
	}
	
	private static String pad(int c) {
	    if (c >= 10)
	        return String.valueOf(c);
	    else
	        return "0" + String.valueOf(c);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case TIME_DIALOG_ID:
	        OnTimeSetListener mTimeSetListener = null;
			return new TimePickerDialog(this,
	                mTimeSetListener, mHour, mMinute, false);
	    }
	    return null;
	}
	*/
}
