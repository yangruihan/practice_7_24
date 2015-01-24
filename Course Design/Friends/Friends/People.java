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
	public static int COL_CLM = 1; 				// college classmates
	public static int HI_CLM = COL_CLM + 1; 	// high school classmates
	public static int FAM = HI_CLM + 1; 		// family member 
	public static int FRI = FAM + 1; 			// friends
	public static int TEA = FRI + 1; 			// teachers 
	public static int RES = TEA + 1; 			// reservation

	private int ID_number; 			// ID of the student
	private String name; 			// name of the student
	private String name_pinyin; 	// name's pinyin of the student
	private Date birthday; 			// birthday of the student
	private String phone_num1; 		// first phone number of the student
	private String phone_num2; 		// second phone number of the student
	private String phone_num3; 		// third phone number of the student 
	private String location; 		// current location of the student 
	private int group; 				// group of the student

	public People(int group, String name, Date birthday, String phone_num1,
			String phone_num2, String phone_num3, String location) {
		PEOPLE_NUMBER++; // ID 
		this.group = group;
		this.ID_number = PEOPLE_NUMBER;
		this.name = name;
		this.birthday = birthday;
		this.phone_num1 = phone_num1;
		this.phone_num2 = phone_num2;
		this.phone_num3 = phone_num3;
		this.location = location;
		calNamePinyin();
	}

	public People(int group, String name, Date birthday, String phone_num1,
			String phone_num2, String location) {
		this(group, name, birthday, phone_num1, phone_num2, "NULL", location);
	}

	public People(int group, String name, Date birthday, String phone_num1,
			String location) {
		this(group, name, birthday, phone_num1, "NULL", location);
	}

	// Cn to pinyin
	private void calNamePinyin() {
		this.name_pinyin = Tools.Cn2Spell.getFullSpell(this.name);
	}

	@Override
	public String toString() {
		return "ID:" + this.ID_number+"\n" +
				   "Name:" + this.name + "\n" + 
				   "Name(Pinyin):" + this.name_pinyin + "\n" + 
				   "Birth:" + this.birthday.toString() + "\n" + 
				   "Phone 1:" + this.phone_num1 + "\n" + 
				   "Phone 2:" + this.phone_num2 + "\n" + 
				   "Phone 3:" + this.phone_num3 + "\n" + 
				   "Current Location:" + this.location + "\n" + 
				   "Group" + this.group;
	}

	// //////////////////////////////////////////////////////////////////
	//
	// get && set methods
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone_num1() {
		return phone_num1;
	}

	public void setPhone_num1(String phone_num1) {
		this.phone_num1 = phone_num1;
	}

	public String getPhone_num2() {
		return phone_num2;
	}

	public void setPhone_num2(String phone_num2) {
		this.phone_num2 = phone_num2;
	}

	public String getPhone_num3() {
		return phone_num3;
	}

	public void setPhone_num3(String phone_num3) {
		this.phone_num3 = phone_num3;
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

	public void setName(String name) {
		this.name = name;
		calNamePinyin();
	}

	public String getName() {
		return this.name;
	}

	public String getNamePinyin() {
		return this.name_pinyin;
	}

}
