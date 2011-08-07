package com.rdft.foreveralone;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rdft.foreveralone.R;

public class Friends extends ListActivity {
	Intent intention; // a reusable intent
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        String[] friends = getResources().getStringArray(R.array.FRIENDS);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.friends, friends));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        /* When a friend's name is pressed, show various details about that
         * 	friend.
         */
        
	}
	// For the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.friendsoptionsmenu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/* This function will handle the tapped icons in the options
		 * menu.
		 */
		switch(item.getItemId()) {
		case R.id.addfriendicon:
			// show the add friend screen
			intention = new Intent(this, AddFriends.class);
			startActivity(intention);
			break;
		case R.id.editfriendsicon:
			Toast.makeText(this, "Edit friends selected", Toast.LENGTH_LONG).show();
			break;
		case R.id.friendsrefresh:
			Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show();
			break;
		case R.id.friendsexit:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
