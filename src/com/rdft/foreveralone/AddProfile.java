package com.rdft.foreveralone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddProfile extends Activity {
	
	private Intent intention; // a private intent to invoke add univeristy
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createprofile);
		
		// Pre-set the intention for later
		intention = new Intent(this, AddUniversity.class);
		
		// Add University button's listener
		final Button addUniversityButton = (Button)findViewById(R.id.adduniversitybutton);
		addUniversityButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Invoke add university
				startActivity(intention);
			}
		});
		
		
		// Submit button's listener
		final Button searchButton = (Button)findViewById(R.id.submitprofile);
		searchButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
		        finish();
			}
		});
	}
}
