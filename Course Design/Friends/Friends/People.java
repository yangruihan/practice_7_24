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

	public static int PEOPLE_NUMBER = 0; // 当前人数

	private int IDNumber; // ID
	private String name; // 姓名
	private String namePinyin; // 姓名拼音
	private String nameHeadChar; // 姓名拼音大写头字母
	private Date birthday; // 生日
	private String phoneNum1; // 电话号码 1
	private String phoneNum2; // 电话号码 2
	private String QQNum; // QQ号
	private String location; // 所在地
	private String gender; // 性别
	private int group; // 群组
	private String groupName; // 群组名

	public People(int group, String name, String gender, Date birthday,
			String phone_num1, String phone_num2, String QQNum, String location) {
		PEOPLE_NUMBER++; // ID
		this.group = group;
		this.IDNumber = PEOPLE_NUMBER;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNum1 = phone_num1;
		this.phoneNum2 = phone_num2;
		this.QQNum = QQNum;
		this.location = location;
		this.namePinyin = calNamePinyin();
		this.nameHeadChar = getNameHeadChar();
		this.groupName = Client.group.get(group);
	}
	
	public People() {
		
	}

	public People(int group, String name, String gender, Date birthday,
			String phone_num1, String qq_num, String location) {
		this(group, name, gender, birthday, phone_num1, "/", qq_num, location);
	}

	public People(int group, String name, String gender, Date birthday,
			String phone_num1, String location) {
		this(group, name, gender, birthday, phone_num1, "/", location);
	}

	// 中文转化成拼音
	private String calNamePinyin() {
		return Tools.CnToSpell.getPinYin(this.name);
	}

	// 获得姓名大写字母
	private String getNameHeadChar() {
		return Tools.CnToSpell.getPinYinHeadChar(this.name);
	}

	@Override
	public String toString() {
		return "------------------------------\n" + "ID:" + this.IDNumber + "\n"
				+ "\t姓名：" + this.name + "\n" + "\t性别：" + this.gender + "\n"
				+ "\t生日：" + this.birthday.toString() + "\n" + "\t电话号码 1："
				+ this.phoneNum1 + "\n" + "\t电话号码 2：" + this.phoneNum2 + "\n"
				+ "\tQQ号码：" + this.QQNum + "\n" + "\t所在地：" + this.location
				+ "\n" + "\t关系：" + this.groupName
				+ "\n------------------------------\n";
	}

	// //////////////////////////////////////////////////////////////////
	//
	// get && set methods
	
	public String getHeadChar() {
		return this.nameHeadChar;
	}

	public static int getPEOPLE_NUMBER() {
		return PEOPLE_NUMBER;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public void setBirthday(String birthday) {
		String strs[] = birthday.split("-");
		this.birthday = new Date(Integer.parseInt(strs[0]),
				Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
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

	public String getQQNum() {
		return QQNum;
	}

	public void setQQNum(String QQNum) {
		this.QQNum = QQNum;
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
		this.groupName = Client.group.get(group);
	}
}
