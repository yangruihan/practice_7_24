package Friends;

public class Client {

	// the number of tasks
	public static int MAIN_MENU = 1001; // the main menu
	public static int IMPORT = 1002; // import into address list
	public static int SHOW = 1003;  // show the address list
	public static int SEARCH = 1004; // search someone

	public Client() {

	}

	public static void main(String[] args) {

		System.out.println("------------- 个人通讯录 -------------");
		System.out
				.println("                                          By Y.R.H\n");
		
		People people = new People(1, "小明", new Date(1995,3,6), "15671651459", "武汉");
		
		System.out.println(people.toString());
		System.out.println(Tools.CnToSpell.getPinYin("小明"));

		String fileName = "Address List.txt";
		Tools.ReadFromFile.readFileByLines(fileName);
		
	}
}
