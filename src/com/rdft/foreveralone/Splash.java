package com.rdft.foreveralone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {
	// ===========================================================
    // Fields
    // ==========================================================
    private final int SPLASH_DISPLAY_LENGTH = 2500;
    // ===========================================================
    // "Constructors"
    // ===========================================================
    /** Called when the activity is first created. */
    private int profile; // determines whether a profile is loaded
    private Intent intention; // a private intent to invoke activities
    
    @Override
    public void onCreate(Bundle trololo) {

            super.onCreate(trololo);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.splash);
    		intention = new Intent(this, AddProfile.class);

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/

            new Handler().postDelayed(new Runnable(){
                    public void run() {
                            /* Create an Intent that will start the Menu-Activity. */
                            Intent mainIntent = new Intent(Splash.this,Menu.class);
                            Splash.this.startActivity(mainIntent);
                            Splash.this.finish();
                            
                            if(profile == 0)
                            {
                            	// If there's no profile loaded
                				startActivity(intention);
                            }
                    }
            }, SPLASH_DISPLAY_LENGTH);
    }
}
