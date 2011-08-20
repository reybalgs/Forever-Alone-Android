package com.rdft.foreveralone.glue.models;

public abstract class Day {
	/*
	 * Forever Alone uses numbers OR'd together to mark days.
	 */
	public final int MON = 0x01;
	public final int TUE = 0x02;
	public final int WED = 0x04;
	public final int THU = 0x08;
	public final int FRI = 0x10;
	public final int SAT = 0x20;
}
