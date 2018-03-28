package com.example.group10.silenceme;

/**
 * Created by MONSTER on 15-Dec-16.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class MainEvent {
	private String startDate;
	private String endDate;
    private String name;
    private String repeat;
	private String location;
    
    
    public MainEvent(String startDate,String endDate,String name,String location){
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.name = name;
    	this.repeat = repeat;
		this.location=location;
    }
	public MainEvent() {
		startDate ="";
		endDate ="";
		name ="";
		repeat ="";
		location="";

	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String toString()
	{

		String output = "Start Date;"+startDate+", End Date;"+endDate+", Repeat;"+repeat+", Location;"+location;

		return output;
	}

}
