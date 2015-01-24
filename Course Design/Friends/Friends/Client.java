package Friends;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Client {

	// 用来存放分组信息的hashmap
	public static HashMap<Integer, String> group = new HashMap<Integer, String>();

	// 用来存放联系人的hashmap
	public static HashMap<Integer, People> contacts = new HashMap<Integer, People>();

	// the file kind
	public static int KIND_GROUP = -99; // 分组信息
	public static int KIND_CONTACTS = -90; // 通讯录内容

	// the number of tasks
	public static int MAIN_MENU = 1001; // the main menu
	public static int IMPORT = 1002; // import into address list
	public static int SHOW = 1003; // show the address list
	public static int SEARCH = 1004; // search someone

	public Client() {

	}

	public void run() {
		Scanner scan = new Scanner(System.in);

		try {
			String userKey;
			
			refresh();

			System.out.println("------------- 个人通讯录 -------------");
			System.out.println("                            By Y.R.H\n");

			while (true) {

				System.out.println("选项：");
				System.out.println("   1.导入通讯录");
				System.out.println("   0.退出\n");
				System.out.println("请用户选择[1, 0]");
				System.out.print("root@主界面> ");

				userKey = scan.nextLine();

				switch (userKey) {
				case "1":
					refresh();
					importAdd(scan);
					break;
				case "0":
					refresh();
					System.out.println("系统正在退出中，请稍等...");
					System.out.println("谢谢使用～");
					System.exit(0);
					break;
				default:
					refresh();
					break;
				}// end of switch

			}// end of while
		} finally {
			if (scan != null) {
				try {
					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// end of if
		}// end of finally

	}

	private void importAdd(Scanner scan) {
		refresh(1);

		System.out.println("1)导入分组信息：");
		System.out.print("   请输入文件名[Group.txt]： \nroot@主界面\\导入通讯录> ");
		String groupFileName = scan.nextLine();
		Tools.ReadFromFile.readFileByLines(groupFileName, KIND_GROUP);
		
		
		
		refresh();
		System.out.println("正在导入分组信息，请稍后...");
		refresh();
		System.out.println("\n导入成功\n");

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		while (true) {
			System.out.println("\n\n2)导入联系人信息：");
			System.out.print("   请输入文件名[Contacts.txt]： \nroot@主界面\\导入通讯录> ");
			String contactsFileName = scan.nextLine();
			Tools.ReadFromFile.readFileByLines(contactsFileName, KIND_CONTACTS);
			refresh();
			System.out.println("正在导入联系人信息，请稍后...");
			refresh();
			System.out.println("\n导入成功\n");
			Iterator<Entry<Integer, People>> iter2 = contacts.entrySet()
					.iterator();
			while (iter2.hasNext()) {
				Map.Entry<Integer, People> entry = (Map.Entry<Integer, People>) iter2
						.next();
				System.out.println(((People) entry.getValue()).toString());
			}

			System.out.println("\n");
			System.out.println("  1.继续导入联系人");
			System.out.println("  2.返回主菜单");
			System.out.print("root@主界面\\导入通讯录> ");
			
			String key = scan.nextLine();
			
			if (key.equals("1")) {
				continue;
			} 
			if (key.equals("2")) {
				refresh();
				break;
			}
		}
		// refresh();
	}

	// 刷新控制台
	private void refresh() {
		refresh(20);
	}

	private void refresh(int time) {
		for (int i = 0; i < time; i++) {
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.run();
	}

}
