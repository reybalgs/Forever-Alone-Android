package com.rdft.foreveralone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddCourse extends Activity 
{
	/* This class is the activity
	 * for the adding of courses. (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// Activity class for adding a new course.
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcourse); // the layout
		
		// The spinner attributes
		Spinner termsSpinner = (Spinner) findViewById(R.id.termchooser);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.numOfTerms, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    termsSpinner.setAdapter(adapter);
		
		// Button's listener
		final Button createCourseButton = (Button)findViewById(R.id.submitcourse);
		createCourseButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// Pat change this button to whatever you need
				finish();
			}
		});
	}
}
