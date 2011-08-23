package com.rdft.foreveralone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddUniversity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduniversity);
		
		// Button's listener
		final Button searchButton = (Button)findViewById(R.id.submituniversity);
		searchButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
		        finish();
			}
		});
	}
}
