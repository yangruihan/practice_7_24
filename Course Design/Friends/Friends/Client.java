package Friends;

public class Client {

	public static int MAIN_MENU = 1001; // ���˵����
	public static int IMPORT = 1002; // ����ͨѶ¼
	public static int SHOW = 1003; // ��ʾͨѶ¼
	public static int SEARCH = 1004; // ����

	public Client() {

	}

	public static void main(String[] args) {

		System.out.println("------------- ����ͨѶ¼ -------------");
		System.out
				.println("                                          By Y.R.H\n");
		System.out.println("1.  ����ͨѶ¼");
		System.out.println("2.  ��ʾͨѶ¼");
		System.out.println("3.  ����");
		System.out.println("4.  �޸�ͨѶ¼");

		Date date = new Date(1995, 9, 23);
		People people = new People("�", date, "15671651459", "�ȳ�", People.FAM);
		System.out.println(people.getName());
		System.out.println(people.toString());

	}
}
