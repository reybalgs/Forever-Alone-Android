package com.rdft.foreveralone;

import com.rdft.foreveralone.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;

public class You extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] schedule = getResources().getStringArray(R.array.SCHED);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.you, schedule));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        /* Fake Progress bar */
        ProgressDialog fakeProgress;
        fakeProgress = new ProgressDialog(this);
        fakeProgress.setMessage("Please wait for 3 valve years...");
        fakeProgress.setCancelable(true);
        /*
        try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.out.println("Error! :(");
		}
        fakeProgress.dismiss();
        */
        
        Toast.makeText(getApplicationContext(), "Click on a course to see more information", Toast.LENGTH_LONG).show();
        
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
              */
          }
          
        });
  		
        
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	  super.onCreateContextMenu(menu, v, menuInfo);
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.layout.course, menu);
	}
	
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog = null;
	    final int DIALOG_PAUSED_ID = 0;
	    final int DIALOG_GAMEOVER_ID = 1;
	    
	    switch(id) {
	    case DIALOG_PAUSED_ID:
	        // do the work to define the pause Dialog
	    	
	        break;
	    case DIALOG_GAMEOVER_ID:
	        // do the work to define the game over Dialog
	        break;
	    default:
	        dialog = null;
	        
	    }
	    showDialog(DIALOG_PAUSED_ID);
	    return dialog;
	}
}

