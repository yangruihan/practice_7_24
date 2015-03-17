package Client;

import Tools.Tools;

/**
 * People �����������ϵ����Ϣ������
 * 
 * �û��ṩ����ţ�ID���� ������name�� �� �Ա�gender���� ���գ�birth��
 * �绰1��phone1�����绰2��phone2�����绰3��phone3�������ڵأ�loc�������飨group��
 * 
 * �Զ����ɣ�����ƴ��(pinyin)��������(groupName)
 * 
 * @author ����
 * @version 1.0
 *
 */
// ��� ���� �Ա� ���� �绰1 �绰2 �绰3 ���ڵ� ����3
public class People {

	private static int NUM_OF_PEOPLE = 0; // ��һ����̬int�������洢��ǰһ���ж�����

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
	private String phone3; // ����
	private String loc;
	private String group;

	private String groupName; // ������
	private String pinyin; // ����ƴ��

	// �����в����Ĺ��췽��
	public People(String name, String gender, String birth, String phone1,
			String phone2, String phone3, String loc, String group) {
		this.ID = String.valueOf((++NUM_OF_PEOPLE)); // ID
														// ֻ����ϵͳ���䣬ÿ��������һ��Peopleʵ����
														// NUM_OF_PEOPLE + 1
		// �����Ը�ֵ
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.loc = loc;
		this.group = group;

		// ˢ��һ�¸�People������ƴ��
		updatePinyin();
	}

	// ֻ��2���ֻ����루�ֻ�1 �� �������Ĺ��췽��
	public People(String name, String gender, String birth, String phone1,
			String phone3, String loc, String group) {
		this(name, gender, birth, phone1, "��", phone3, loc, group);
	}

	// ֻ��1���ֻ�����Ĺ��췽��
	public People(String name, String gender, String birth, String phone1,
			String loc, String group) {
		this(name, gender, birth, phone1, "��", "��", loc, group);
	}

	// ��ϸ��ӡPeople��Ϣ
	public void showD() {
		System.out.println("-----" + this.ID + "." + this.name + "-----");
		System.out.println("-ƴ����" + this.pinyin);
		System.out.println("-�Ա�" + this.gender);
		System.out.println("-���գ�" + this.birth);
		System.out.println("-�绰����1��" + this.phone1);
		System.out.println("-�绰����2��" + this.phone2);
		System.out.println("-�������룺" + this.phone3);
		System.out.println("-���ڵأ�" + this.loc);
		System.out.println("-���飺" + this.groupName);
		System.out.println("---------------");
		System.out.println();
	}

	// ���Դ�ӡPeople��Ϣ
	public void showR() {
		System.out.println("---------" + this.ID + "." + this.name
				+ "---------");
		System.out.println("-�Ա�" + this.gender + "   ���գ�" + this.birth
				+ "\n-�绰���룺" + this.phone1 + "   ���ڵأ�" + this.loc + "   ���飺"
				+ this.group + "\n");
	}

	// ���ص�ǰPeople����
	public int getPeopleNum() {
		return NUM_OF_PEOPLE;
	}

	// �������ļ�����ʱ�����·�����
	public void updateGroupName(String groupName) {
		this.groupName = groupName;
	}

	// �� People ʵ������ʱ�Լ� People ��������ʱ ˢ��ƴ����
	public void updatePinyin() {
		this.pinyin = Tools.getPinYin(this.name); // ���ù������getPinyin����
	}

	// get��set����
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
