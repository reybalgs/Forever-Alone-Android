package com.rdft.foreveralone.glue.debug;

import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.GlueService;
import com.rdft.foreveralone.glue.GlueService.LocalBinder;
import com.rdft.foreveralone.glue.models.University;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class NetworkCodeActivity extends Activity {
    GlueService mService;
    boolean mBound = false;
    String TAG = "NETCODE";
    CommHandler comm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glue_debug);
        comm = new CommHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to DataService
        Intent intent = new Intent(this, GlueService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        
        Toast.makeText(this, "POOTIS SPENCER HERE", Toast.LENGTH_SHORT);
        Log.i(TAG, "POOTIS SPENCER HERE");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    /** Called when a button is clicked (the button in the layout file attaches to
      * this method with the android:onClick attribute) */
    public void onButtonClick(View v) {
        if (mBound) {
            // Call a method from the DataService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: " + num, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "NUMBER: " + num);
        } else {
        	Toast.makeText(this, "NOPE", Toast.LENGTH_SHORT);
        	Log.i(TAG, "NOPE!");
        }
    }
    
    public void onYeahButtonClick(View v) {
    	Log.i(TAG, "YEAH!");
    	University[] unis = comm.getUniversities();
    	for (University uni : unis) {
    		Log.i(TAG, uni.name);
    	}
    }
    
    public void disconnectPeople(View v) {
    	Log.i(TAG, "DISCONNECTING PEOPLE - Now with Swipe.");
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to DataService, cast the IBinder and get DataService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}