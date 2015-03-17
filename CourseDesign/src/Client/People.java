package Client;

import Tools.Tools;

/**
 * People 类用来存放联系人信息包括：
 * 
 * 用户提供：编号（ID）、 姓名（name） 、 性别（gender）、 生日（birth）
 * 电话1（phone1）、电话2（phone2）、电话3（phone3）、所在地（loc）、分组（group）
 * 
 * 自动生成：姓名拼音(pinyin)、分组名(groupName)
 * 
 * @author 范勇
 * @version 1.0
 *
 */
// 编号 姓名 性别 生日 电话1 电话2 电话3 所在地 分组3
public class People {

	private static int NUM_OF_PEOPLE = 0; // 用一个静态int类型来存储当前一共有多少人

	public static int getNUM_OF_PEOPLE() {
		return NUM_OF_PEOPLE;
	}

	public static void setNUM_OF_PEOPLE(int nUM_OF_PEOPLE) {
		NUM_OF_PEOPLE = nUM_OF_PEOPLE;
	}

	private String ID;
	private String name;
	private String gender;
	private String birth;
	private String phone1;
	private String phone2;
	private String phone3; // 座机
	private String loc;
	private String group;

	private String groupName; // 分组名
	private String pinyin; // 姓名拼音

	// 带所有参数的构造方法
	public People(String name, String gender, String birth, String phone1,
			String phone2, String phone3, String loc, String group) {
		this.ID = String.valueOf((++NUM_OF_PEOPLE)); // ID
														// 只能由系统分配，每次新生成一个People实例，
														// NUM_OF_PEOPLE + 1
		// 将属性赋值
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.loc = loc;
		this.group = group;

		// 刷新一下该People的姓名拼音
		updatePinyin();
	}

	// 只有2个手机号码（手机1 和 座机）的构造方法
	public People(String name, String gender, String birth, String phone1,
			String phone3, String loc, String group) {
		this(name, gender, birth, phone1, "无", phone3, loc, group);
	}

	// 只有1个手机号码的构造方法
	public People(String name, String gender, String birth, String phone1,
			String loc, String group) {
		this(name, gender, birth, phone1, "无", "无", loc, group);
	}

	// 详细打印People信息
	public void showD() {
		System.out.println("-----" + this.ID + "." + this.name + "-----");
		System.out.println("-拼音：" + this.pinyin);
		System.out.println("-性别：" + this.gender);
		System.out.println("-生日：" + this.birth);
		System.out.println("-电话号码1：" + this.phone1);
		System.out.println("-电话号码2：" + this.phone2);
		System.out.println("-座机号码：" + this.phone3);
		System.out.println("-所在地：" + this.loc);
		System.out.println("-分组：" + this.groupName);
		System.out.println("---------------");
		System.out.println();
	}

	// 简略打印People信息
	public void showR() {
		System.out.println("---------" + this.ID + "." + this.name
				+ "---------");
		System.out.println("-性别：" + this.gender + "   生日：" + this.birth
				+ "\n-电话号码：" + this.phone1 + "   所在地：" + this.loc + "   分组："
				+ this.group + "\n");
	}

	// 返回当前People人数
	public int getPeopleNum() {
		return NUM_OF_PEOPLE;
	}

	// 当分组文件导入时，更新分组名
	public void updateGroupName(String groupName) {
		this.groupName = groupName;
	}

	// 当 People 实例生成时以及 People 姓名更改时 刷新拼音用
	public void updatePinyin() {
		this.pinyin = Tools.getPinYin(this.name); // 调用工具类的getPinyin方法
	}

	// get、set方法
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
