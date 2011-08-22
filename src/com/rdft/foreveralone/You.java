package com.rdft.foreveralone;

import java.util.ArrayList;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.models.Course;
import com.rdft.foreveralone.glue.models.Section;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.*;
import android.view.Menu;
import com.rdft.foreveralone.DummyValues;

public class You extends ListActivity {
	/* Some variables for the options menu
	private int addButtonID = Menu.FIRST;
	private int sortButtonID = Menu.FIRST + 1;
	private int exitButtonID = Menu.FIRST + 2;
	private int group1ID = 1;
	*/
	// Just an intent for invoking subactivities
	Intent intention;
	private ArrayList<Section> m_sections = null;
	private YouSectionAdapter m_adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        m_sections = new ArrayList<Section>();
        m_sections.add(DummyValues.fakeSection);
        this.m_adapter = new YouSectionAdapter(this, R.layout.you, m_sections);
        setListAdapter(this.m_adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        /*
        Fake Progress bar
        ProgressDialog fakeProgress;
        fakeProgress = new ProgressDialog(this);
        fakeProgress.setMessage("Please wait for 3 valve years...");
        fakeProgress.setCancelable(true);
        */
       /*
=======
        /*
>>>>>>> 36e0e15992914854c1de15af757e762ea8394c98
        try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.out.println("Error! :(");
		}
        fakeProgress.dismiss();
        */
        
        Toast.makeText(getApplicationContext(), "Click on a course to see more information", Toast.LENGTH_LONG).show();
        /*
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
        	  
        	  /* When an item in the list is clicked, information regarding
        	   * that item is displayed in a neat alertdialog.
        	   */
        	 /*
        	  AlertDialog.Builder builder;
        	  AlertDialog alertDialog;
        	  
        	  // Create the alert box
              AlertDialog.Builder alertbox = new AlertDialog.Builder(You.this);

              // Set the message to display
              
              FrameLayout fl = (FrameLayout) findViewById(android.R.id.youpopup);
              fl.addView(myView, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
				
              
              alertbox.setContextView(R.id.youpopup);

              // Add a neutral button to the alert box and assign a click listener
              alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                  // Click listener on the neutral button of alert box
                  public void onClick(DialogInterface arg0, int arg1) {

                      // The neutral button was clicked
                      Toast.makeText(getApplicationContext(), "Yipee-Ki-Yay!", Toast.LENGTH_LONG).show();
                  }
              });

               // show the alert box
              alertbox.show();
              
          }
          
        });
  		*/
        
	}

	/*
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.menu.youoptionsmenu, menu);
	}
	*/
	/*
	@Override
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog = null;
	    // Reserved area for dialogs
	    return dialog;
	}
	*/
	// For the options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.youoptionsmenu, menu);
	    return true;
	}
	
	// For the butons in the options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/* This function will handle the tapped icons in the options
		 * menu.
		 */
		switch(item.getItemId()) {
		case R.id.addyouicon:
			// Gets the addcourse screen activity
			intention = new Intent(this, AddCourse.class);
			startActivity(intention);
			break;
		case R.id.edityouicon:
			intention = new Intent(this, AddProfile.class);
			startActivity(intention);
			break;
		case R.id.refreshyouicon:
			Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show();
			break;
		case R.id.exityouicon:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}

