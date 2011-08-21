package com.rdft.foreveralone;

import java.util.ArrayList;

import com.rdft.foreveralone.glue.models.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class YouCourseAdapter extends ArrayAdapter<Course> 
{
	// This is the adapter for courses to be viewed in the You tab
	
	private ArrayList<Course> courses; // Arraylist for the courses
	
	public YouCourseAdapter(Context context, int textViewResourceId, ArrayList<Course> courses)
	{
		super(context, textViewResourceId, courses);
		this.courses = courses;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.you, null);
		}
		Course o = courses.get(position);
		if(o != null) // If the course isn't empty or something
		{
			// Gets the IDs of the textviews in the XML layout
			TextView tt = (TextView) v.findViewById(R.id.coursecode);
			TextView bt = (TextView) v.findViewById(R.id.coursedesc);
			
			if(tt != null) // If the coursecode field is not empty
			{
				tt.setText(o.getCourseCode()); // Gets the course code and sets it as the text
			}
			if(bt != null) // If the time field is not empty
			{
				bt.setText(o.getDescription());
			}
		}
		return v;
	}
}
