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

	public static int PEOPLE_NUMBER = 0; // �������
	public static int COL_CLM = 1; // ��ѧͬѧ
	public static int HI_CLM = COL_CLM + 1; // ����ͬѧ
	public static int FAM = HI_CLM + 1; // ����
	public static int FRI = FAM + 1; // ����
	public static int TEA = FRI + 1; // ��ʦ
	public static int RES = TEA + 1; // ����

	private int ID_number; // ��� * ���ɸ��ģ�ֻ����ϵͳ����
	private String name; // ����
	private Date birthday; // ����
	private String phone_num1; // �ֻ���1
	private String phone_num2; // �ֻ���2
	private String phone_num3; // �ֻ���3
	private String location; // ���ڵ�
	private int group; // ����Ⱥ��

	// ������ȫ�Ĺ��캯��
	public People(String name, Date birthday, String phone_num1,
			String phone_num2, String phone_num3, String location, int group) {
		PEOPLE_NUMBER++; // ������ż�1

		this.ID_number = PEOPLE_NUMBER;
		this.birthday = birthday;
		this.phone_num1 = phone_num1;
		this.phone_num2 = phone_num2;
		this.phone_num3 = phone_num3;
		this.location = location;
		this.group = group;
	}

	// ֻ��2���ֻ��ŵĹ��캯��
	public People(String name, Date birthday, String phone_num1,
			String phone_num2, String location, int group) {
		this(name, birthday, phone_num1, phone_num2, "NULL", location, group);
	}

	// ֻ��1���ֻ��ŵĹ��캯��
	public People(String name, Date birthday, String phone_num1,
			String location, int group) {
		this(name, birthday, phone_num1, "NULL", "NULL", location, group);
	}

	// //////////////////////////////////////////////////////////////////
	//
	// get && set ����
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
	}

	public String getName() {
		return this.name;
	}

}
