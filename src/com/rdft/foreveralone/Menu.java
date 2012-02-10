package com.rdft.foreveralone;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import com.rdft.foreveralone.R;
import com.rdft.foreveralone.glue.CommHandler;
import com.rdft.foreveralone.glue.auth.LoginTask;

public class Menu extends TabActivity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, You.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("you").setIndicator("",
                      res.getDrawable(R.drawable.ic_tab_you))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, FriendsView.class);
       spec = tabHost.newTabSpec("friends").setIndicator("",
                          res.getDrawable(R.drawable.ic_tab_friends))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Settings.class);
        spec = tabHost.newTabSpec("settings").setIndicator("",
                          res.getDrawable(R.drawable.ic_tab_settings))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
        

    }
}