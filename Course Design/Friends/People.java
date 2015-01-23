/**
 * Filename:"People"
 * Author:"Yang Ruihan"
 * Time:2015/1/23
 **/
Package Friends;

import Date;

public class People {

	static PEOPLE_NUMBER = 0;
	static COL_CLM = 1001;
	static HI_CLM = COL_FRI + 1;
	static FAM = HI_FRI + 1;
	static FRI = FAM + 1;
	static TEA = FRI + 1;
	static RES = TEA + 1;

	int ID_number; 
	String name;
	Date birthday;
	String phone_num1;
	String phone_num2;
	String phone_num3;
	String location;
	int group;

	People(String name, 
		   Date birthday,
		   String phone_num1,
		   String phone_num2,
		   String phone_num3,
		   String location,
		   int group) {
		PEOPLE_NUMBER++;
		this.ID_number = PEOPLE_NUMBER;

	}
}