package Friends;

public class Client {

	public static int MAIN_MENU = 1001; // 主菜单编号
	public static int IMPORT = 1002; // 导入通讯录
	public static int SHOW = 1003; // 显示通讯录
	public static int SEARCH = 1004; // 查找

	public Client() {

	}

	public static void main(String[] args) {

		System.out.println("------------- 个人通讯录 -------------");
		System.out
				.println("                                          By Y.R.H\n");
		System.out.println("1.  导入通讯录");
		System.out.println("2.  显示通讯录");
		System.out.println("3.  查找");
		System.out.println("4.  修改通讯录");

		Date date = new Date(1995, 9, 23);
		People people = new People("睿", date, "15671651459", "谷城", People.FAM);
		System.out.println(people.getName());
		System.out.println(people.toString());

	}
}
