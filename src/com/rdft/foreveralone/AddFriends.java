package com.rdft.foreveralone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class AddFriends extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addfriend);
		
		// Button's listener
		final Button searchButton = (Button)findViewById(R.id.addfriends_searchbutton);
		searchButton.setOnClickListener(new OnClickListener() 
		{
			
			public void onClick(View v) 
			{
		        finish();
			}
		});
	}
}
