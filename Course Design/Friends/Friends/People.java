/**
 * Filename:"People.java"
 * Author:"Yang Ruihan"
 * Time:2015-1-23
 **/
package Friends;

public class People {

	/**
	 * ClassName:People
	 **/

	public static int PEOPLE_NUMBER = 0; 		// the number of people in address list

	private int IDNumber; 			// ID of the student
	private String name; 			// name of the student
	private String namePinyin; 	// name's pinyin of the student
	private String nameHeadChar;	// name's head char
	private Date birthday; 			// birthday of the student
	private String phoneNum1; 		// first phone number of the student
	private String phoneNum2; 		// second phone number of the student
	private String phoneNum3; 		// third phone number of the student 
	private String location; 		// current location of the student 
	private String gender;
	private int group; 				// group of the student

	public People(int group, String name, String gender, Date birthday, String phone_num1,
			String phone_num2, String phone_num3, String location) {
		PEOPLE_NUMBER++; // ID 
		this.group = group;
		this.IDNumber = PEOPLE_NUMBER;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNum1 = phone_num1;
		this.phoneNum2 = phone_num2;
		this.phoneNum3 = phone_num3;
		this.location = location;
		this.namePinyin = calNamePinyin();
		this.nameHeadChar = getNameHeadChar();
	}

	public People(int group, String name, String gender, Date birthday, String phone_num1,
			String phone_num2, String location) {
		this(group, name, gender, birthday, phone_num1, phone_num2, "NULL", location);
	}

	public People(int group, String name, String gender, Date birthday, String phone_num1,
			String location) {
		this(group, name, gender, birthday, phone_num1, "NULL", location);
	}

	// Cn to pinyin
	private String calNamePinyin() {
		return Tools.CnToSpell.getPinYin(this.name);
	}
	
	// get name pinyin head char
	private String getNameHeadChar() {
		return Tools.CnToSpell.getPinYinHeadChar(this.name);
	}

	@Override
	public String toString() {
		return "------------------------------\n" +
				   "ID:" + this.IDNumber+" " +
				   "\t姓名：" + this.name + "\n" + 
				   "\t性别：" + this.gender + "\n" +
				   "\t生日：" + this.birthday.toString() + "\n" + 
				   "\t电话号码 1：" + this.phoneNum1 + "\n" + 
				   "\t电话号码 2：" + this.phoneNum2 + "\n" + 
				   "\t电话号码 3：" + this.phoneNum3 + "\n" + 
				   "\t所在地：" + this.location +
				   "\n------------------------------\n";
	}
	
	// //////////////////////////////////////////////////////////////////
	//
	// get && set methods
	

	public static int getPEOPLE_NUMBER() {
		return PEOPLE_NUMBER;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static void setPEOPLE_NUMBER(int pEOPLE_NUMBER) {
		PEOPLE_NUMBER = pEOPLE_NUMBER;
	}

	public int getIDNumber() {
		return IDNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.nameHeadChar = getNameHeadChar();
		this.namePinyin = calNamePinyin();
	}

	public String getNamePinyin() {
		return namePinyin;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNum1() {
		return phoneNum1;
	}

	public void setPhoneNum1(String phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public String getPhoneNum2() {
		return phoneNum2;
	}

	public void setPhoneNum2(String phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}

	public String getPhoneNum3() {
		return phoneNum3;
	}

	public void setPhoneNum3(String phoneNum3) {
		this.phoneNum3 = phoneNum3;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
}
