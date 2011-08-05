package com.rdft.foreveralone;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class CourseInfoDialog extends Dialog {
	Button OkayButton;
	public CourseInfoDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// Remove the title
		setTitle("Details about DIGIDES");
		setContentView(R.layout.digiderp_details);
	}

	public CourseInfoDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public CourseInfoDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	
	public void kaBoom(View v)
	{
		// Demoman's method.
		dismiss();
	}
}
