package com.rdft.foreveralone;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rdft.foreveralone.glue.models.Friend;
import com.rdft.foreveralone.glue.models.Section;

public class FriendsListAdapter extends BaseAdapter {

	// This is the adapter for listing the friends in the
	// Friends tab.
	
	// A private array for friends
	//private static ArrayList<Friend> friends;
	
	public FriendsListAdapter()
	{
		/*
		super(context, textViewResourceId, friends);
		this.friends = friends; */
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TOhDO Auto-generated method stub
		return 0;
	}

	public View getView() {
		// TODO Auto-generated method stub
		/*
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.you, null);
		}
		Friend o = friends.get(position);
		if(o != null) // If the course isn't empty or something
		{
			// Gets the IDs of the textviews in the XML layout
			TextView tt = (TextView) v.findViewById(R.id.coursecode);
			TextView bt = (TextView) v.findViewById(R.id.coursedesc);
			
			if(tt != null) // If the coursecode field is not empty
			{
				tt.setText(o.course.getCourseCode()); // Gets the course code and sets it as the text
			}
			if(bt != null) // If the time field is not empty
			{
				bt.setText(o.course.getDescription());
			}
		}
		*/
		return null;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
