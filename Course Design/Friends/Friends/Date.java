/**
 * Filename:"Date.java"
 * Author:"Yang Ruihan"
 * Time:2015-1-23
 **/

package Friends;

public class Date {

	/**
	 * ClassName:Date
	 **/

	private int year;
	private int month;
	private int day;

	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public String toString() {
		return this.year + "-" + this.month + "-" + this.day;
	}

	// //////////////////////////////////////////////////////////////////
	//
	// get && set ·½·¨
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}