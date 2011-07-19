package com.rdft.foreveralone;

import com.rdft.foreveralone.R;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.view.*;

public class You extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] schedule = getResources().getStringArray(R.array.SCHED);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.you, schedule));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            // When clicked, shows a popup with schedule information
        	LayoutInflater inflater = (LayoutInflater)You.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.you_popup_layout,null,false),300,400,true);
        	
        	// Shows the location of the popup at the center.
        	pw.showAtLocation(findViewById(R.id.you), Gravity.CENTER,0,0);
        	
        	
          }
          
        });
	}
	

}
