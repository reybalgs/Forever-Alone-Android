package com.rdft.foreveralone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AddProfile extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createprofile);
		
		// Button's listener
		final Button searchButton = (Button)findViewById(R.id.submitprofile);
		searchButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
		        Toast.makeText(AddProfile.this, "No matches found!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
