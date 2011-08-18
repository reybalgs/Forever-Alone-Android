package com.rdft.foreveralone.glue.models;

import java.util.Date;

public class Meeting extends DatastoreEntity {
	Section section;
	
	boolean isSpecificDay;
	Date specificDate;

	int days;
	Date startTime;
	Date endTime;
}
