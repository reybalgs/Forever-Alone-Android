package com.rdft.foreveralone.glue.models;

import java.util.ArrayList;
import java.util.Date;

public class Schedule extends DatastoreEntity {
	String name;
	Date creationDate;
	int term;
	int year;
	ArrayList<Section> classes;
}
