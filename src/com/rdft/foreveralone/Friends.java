package com.rdft.foreveralone;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.rdft.foreveralone.R;

public class Friends extends ListActivity {
	@Override
	/* Trololololo */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] friends = getResources().getStringArray(R.array.FRIENDS);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.friends, friends));

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
	}
}
