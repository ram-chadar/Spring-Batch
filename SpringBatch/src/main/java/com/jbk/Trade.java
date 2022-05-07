package com.jbk;

public class Trade {
	private String direction;
	private int year;
	private String weekday;
	private String country;
	private String commodity;
	private String transportMode;
	private String measure;
	private String value;
	private String date;
	
	public Trade() {
	
	}

	public Trade(String direction, int year, String weekday, String country, String commodity, String transportMode,
			String measure, String value, String date) {
		super();
		this.direction = direction;
		this.year = year;
		this.weekday = weekday;
		this.country = country;
		this.commodity = commodity;
		this.transportMode = transportMode;
		this.measure = measure;
		this.value = value;
		this.date = date;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Trade [direction=" + direction + ", year=" + year + ", weekday=" + weekday + ", country=" + country
				+ ", commodity=" + commodity + ", transportMode=" + transportMode + ", measure=" + measure + ", value="
				+ value + ", date=" + date + "]";
	}
	
	
	}
	