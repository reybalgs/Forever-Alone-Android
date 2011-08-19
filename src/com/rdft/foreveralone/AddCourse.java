package com.rdft.foreveralone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
		
		// Button's listener
		final Button createCourseButton = (Button)findViewById(R.id.addcoursecreatebutton);
		createCourseButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
				// Pat change this button to whatever you need
		        Toast.makeText(AddCourse.this, "Derp derp depr", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
