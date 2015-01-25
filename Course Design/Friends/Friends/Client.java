package Friends;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Client {

	// 用来存放分组信息的hashmap
	public static HashMap<Integer, String> group = null;

	// 用来存放联系人的hashmap
	public static HashMap<Integer, People> contacts = null;

	// 用户信息
	public static String userName;
	public static String userGender;
	public static String userBirth;
	public static String userPhoneNum;
	public static String userPhoneNum2;
	public static String userQQNum;
	public static String userLocation;
	
	public Client() {

	}

	// /////////////////////////////////////////////////////////////////////////////
	public void run() throws InterruptedException {
		Scanner scan = new Scanner(System.in);

		try {
			refresh();

			System.out.println("           欢迎使用个人通讯录\n");

			// 初始化客户端
			initClient(scan);

			// 程序主循环
			mainLoop(scan);

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
	// /////////////////////////////////////////////////////////////////////////////

	private void initClient(Scanner scan) throws InterruptedException {
		if (!fileIsExist("user_info.lib")) {

			System.out.println("首先，完善自己的个人资料:");

			// 初始化用户信息
			initUserInfo(scan);

			Tools.WriteToFile.writeFileByName("user_info.lib",
					Tools.WriteToFile.KIND_USER_INFO);

			refresh();
		} else {
			Tools.ReadFromFile.readFileByLines("user_info.lib",
					Tools.ReadFromFile.KIND_USER_INFO);
		}

		// 显示个人信息
		showUserInfo();

		System.out.print("进入主菜单(yes/no)> ");
		String yesString = scan.nextLine();
		if (yesString.equals("no")) {
			System.out.println("系统正在退出中，请稍等...");
			System.out.print("谢谢使用～");
			System.exit(0);
		} else {
			// 默认从 Group.txt && Contacts.txt 导入数据
			if (fileIsExist("Group.txt") && fileIsExist("Contacts.txt")) {
				if (group == null) {
					group = new HashMap<Integer, String>();
				}
				if (contacts == null) {
					contacts = new HashMap<Integer, People>();
				}
				Tools.ReadFromFile.readFileByLines("Group.txt", Tools.ReadFromFile.KIND_GROUP);
				Tools.ReadFromFile.readFileByLines("Contacts.txt", Tools.ReadFromFile.KIND_CONTACTS);
				
				refresh();
				System.out.print("正在从默认文件(Group.txt, Contacts.txt)中导入数据，请稍等...");
				Thread.sleep(600);
				refresh();
				System.out.print("导入成功");
				Thread.sleep(300);
			}
		}
	}

	// 初始化用户信息
	private void initUserInfo(Scanner scan) {
		System.out.print("姓名> ");
		userName = scan.nextLine();
		System.out.print("性别> ");
		userGender = scan.nextLine();
		System.out.print("生日[xxxx-xx-xx]> ");
		userBirth = scan.nextLine();
		System.out.print("手机号码 1> ");
		userPhoneNum = scan.nextLine();
		System.out.print("手机号码 2(没有填“无”)> ");
		userPhoneNum2 = scan.nextLine();
		System.out.print("QQ号码(没有填“无”)> ");
		userQQNum = scan.nextLine();
		System.out.print("所在地> ");
		userLocation = scan.nextLine();
		System.out.println();
		System.out.println("数据添加成功!");
	}

	private void showUserInfo() {
		System.out.println("-------------- 个人信息 --------------\n");
		System.out.println("- 姓名：" + userName + " -");
		System.out.println("- 性别：" + userGender + " -");
		System.out.println("- 生日：" + userBirth + " -");
		System.out.println("- 手机号码 1：" + userPhoneNum + " -");
		System.out.println("- 手机号码 2：" + userPhoneNum2 + " -");
		System.out.println("- QQ号码：" + userQQNum + " -");
		System.out.println("- 所在地：" + userLocation + " -\n");
		System.out.println("-------------------------------------\n");
	}

	// /////////////////////////////////////////////////////////////////////////////
	// 程序主循环体
	private void mainLoop(Scanner scan) {
		String userKey;
		while (true) {
			refresh();

			System.out.println("       主菜单");
			System.out.println("--------------------\n");
			System.out.println("选项：");
			System.out.println("   1.显示个人信息");
			System.out.println("   2.导入通讯录");
			System.out.println("   3.显示通讯录");
			System.out.println("   4.查找联系人");
			System.out.println("   0.退出\n");
			System.out.print(userName + "@主菜单> ");

			userKey = scan.nextLine();

			switch (userKey) {
			case "1":
				optShowUserInfo(scan);
				break;
			case "2":
				optImportCon(scan);
				break;
			case "3":
				optShowCon(scan);
				break;
			case "4":
				optSearchCon(scan);
				break;
			case "0":
				optExitSys();
				break;
			default:
				refresh();
				break;
			}// end of switch

		}
	}
	// /////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	private void optSearchCon(Scanner scan) {
		refresh();

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("选项：");
		System.out.println("   1.按编号查找");
		System.out.println("   2.按分组查找");
		System.out.println("   3.按姓名查找");
		System.out.println("   4.按姓名拼音查找");
		System.out.println("   5.按手机号码查找");
		System.out.println("   6.按QQ号码查找");
		System.out.println("   7.按所在地查找");
		System.out.println("   9.关键词查找");
		System.out.println("   0.返回主菜单\n");

		System.out.print(userName + "@主菜单\\查找联系人> ");
		String key = scan.nextLine();
		
		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		
		String content;
		boolean result = false;
		switch(key) {
		case "1":
			result = searchConByID(scan, result);
			break;
		case "2":
			refresh();
			
			System.out.println("请选择分组\n");
			
			Iterator<Entry<Integer, String>> iter2 = group.entrySet().iterator();
			while (iter2.hasNext()) {
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter2
						.next();
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			
			
			System.out.print(userName + "@主菜单\\查找联系人\\分组查找> ");
			content = scan.nextLine();
			
			Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();

			while (iter.hasNext()) {
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
						.next();
				if (String.valueOf(entry.getKey()).equals(content)) {
					System.out.println("            " + entry.getValue());
					System.out.println("------------------------------");
	
					Iterator<Entry<Integer, People>> tempIter = contacts.entrySet()
							.iterator();
					while (tempIter.hasNext()) {
						Map.Entry<Integer, People> tempEntry = (Map.Entry<Integer, People>) tempIter
								.next();
						if (tempEntry.getValue().getGroup() == entry.getKey()) {
							System.out.println(((People)tempEntry.getValue()).toString());
							result = true;
						}
					}
				}
			}
			
			break;
		default:
			break;
		}
		
		if (!result) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("   未能找到相关联系人");
		}
		
		System.out.println("\n选项：");
		System.out.println("   1.继续查找");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\查找联系人\\查找结果> ");
		key = scan.nextLine();
		if (key.equals("1")) {
			optSearchCon(scan);
		} else {
			
		}
		
		
	}

	private boolean searchConByID(Scanner scan, boolean result) {
		String content;
		refresh();
		
		System.out.println("请输入联系人编号\n");
		System.out.print(userName + "@主菜单\\查找联系人\\编号查找> ");
		content = scan.nextLine();
		
		Iterator<Entry<Integer, People>> iter = contacts.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<Integer, People> entry = iter.next();
			if (String.valueOf(entry.getKey()).equals(content)) {
				refresh();
				System.out.println("          查找结果");
				System.out.println(((People)entry.getValue()).toString());
				result = true;
				break;
			}
		}
		return result;
	}
	
	private void optShowCon(Scanner scan) {
		refresh();

		// 如果还未导入数据
		if (contacts == null || group == null) {
			System.out.println("       主菜单");
			System.out.println("--------------------\n");
			System.out.println("通讯录无数据，请先导入通讯录\n");
			System.out.println("选项：");
			System.out.println("   1.导入通讯录");
			System.out.println("   0.返回主菜单\n");

			System.out.print(userName + "@主菜单\\显示通讯录> ");

			String key = scan.nextLine();
			// 导入通讯录
			if (key.equals("1")) {
				optImportCon(scan);
				return;
			} else if (key.equals("0")) {
				return;
			}
		}

		// 分组显示联系人
		showConByGroupR();

		System.out.println("选项：");
		System.out.println("   1.显示详细信息");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\显示通讯录> ");
		String key = scan.nextLine();
		if (key.equals("1")) {
			refresh();
			showConByGroupD();
			System.out.println("选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示通讯录\\显示详细信息> ");
			scan.nextLine();
		} else {
			
		}
	}

	// 粗略分组显示联系人
	@SuppressWarnings("unchecked")
	private void showConByGroupR() {
		System.out.println("             通讯录");
		System.out.println("------------------------------\n\n");

		HashMap<Integer, People> temp = (HashMap<Integer, People>) contacts.clone();

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		int[] record = new int[contacts.size()];
		int i = 0;

		while (iter.hasNext()) {
			i = 0;

			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println("            " + entry.getValue());
			System.out.println("------------------------------");

			Iterator<Entry<Integer, People>> tempIter = temp.entrySet()
					.iterator();
			while (tempIter.hasNext()) {
				Map.Entry<Integer, People> tempEntry = (Map.Entry<Integer, People>) tempIter
						.next();
				if (tempEntry.getValue().getGroup() == entry.getKey()) {
					System.out.println(
							"    "+
							((People) tempEntry.getValue()).getName()
							+ "   "
							+ ((People) tempEntry.getValue()).getGender()
							+ "   "
							+ ((People) tempEntry.getValue()).getPhoneNum1());
					record[i++] = tempEntry.getKey();
				}
			}

			for (int j = 0; j < i; j++) {
				temp.remove(record[j]);
				record[j] = 0;
			}

			System.out.println();
		}
	}

	// 详细分组显示联系人
	@SuppressWarnings("unchecked")
	private void showConByGroupD() {
		System.out.println("             通讯录");
		System.out.println("------------------------------\n\n");

		HashMap<Integer, People> temp1 = (HashMap<Integer, People>) contacts.clone();

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		int[] record = new int[contacts.size()];
		int i = 0;

		while (iter.hasNext()) {
			i = 0;

			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println("            " + entry.getValue());
			System.out.println("------------------------------");

			Iterator<Entry<Integer, People>> temp1Iter = temp1.entrySet()
					.iterator();
			while (temp1Iter.hasNext()) {
				Map.Entry<Integer, People> temp1Entry = (Map.Entry<Integer, People>) temp1Iter
						.next();
				if (temp1Entry.getValue().getGroup() == entry.getKey()) {
					System.out.println(((People) temp1Entry.getValue())
							.toString());
					record[i++] = temp1Entry.getKey();
				}
			}

			for (int j = 0; j < i; j++) {
				temp1.remove(record[j]);
				record[j] = 0;
			}

			System.out.println();
		}
	}

	// 退出系统选项
	private void optExitSys() {
		refresh();
		System.out.println("系统正在退出中，请稍等...");
		System.out.print("谢谢使用～");
		System.exit(0);
	}

	// 导入信息选项
	private void optImportCon(Scanner scan) {
		if (group == null) {
			group = new HashMap<Integer, String>();
		}
		if (contacts == null) {
			contacts = new HashMap<Integer, People>();
		}
		refresh();
		importAdd(scan);
	}

	// 显示用户信息选项
	private void optShowUserInfo(Scanner scan) {
		refresh();
		showUserInfo();
		System.out.println("选项：");
		System.out.println("   1.修改个人信息");
		System.out.println("   2.返回主菜单\n");
		System.out.print(userName + "@主菜单\\个人信息> ");
		String pressKey = scan.nextLine();
		// 修改个人信息
		if (pressKey.equals("1")) {
			changeUserInfo(scan);
			pressKey = scan.nextLine();
			while (pressKey.equals("1")) {
				changeUserInfo(scan);
				pressKey = scan.nextLine();
			}
		}
	}

	private void changeUserInfo(Scanner scan) {
		String pressKey;
		refresh();
		System.out.println("     修改个人信息");
		System.out.println("--------------------\n");
		System.out.println("修改选项：");
		System.out.println("   1.姓名");
		System.out.println("   2.性别");
		System.out.println("   3.生日");
		System.out.println("   4.电话号码 1");
		System.out.println("   5.电话号码 2");
		System.out.println("   6.QQ号码");
		System.out.println("   7.所在地");
		System.out.println("   0.全部重置\n");
		System.out.print(userName + "@主菜单\\个人信息\\修改个人信息> ");
		pressKey = scan.nextLine();
		switch (pressKey) {
		case "1":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入姓名：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改姓名> ");
			userName = scan.nextLine();
			break;
		case "2":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入性别：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改性别> ");
			userGender = scan.nextLine();
			break;
		case "3":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入生日[xxxx-xx-xx]：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改生日> ");
			userBirth = scan.nextLine();
			break;
		case "4":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入手机号码：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改手机号码 1> ");
			userPhoneNum = scan.nextLine();
			break;
		case "5":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入手机号码：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改手机号码 2> ");
			userPhoneNum2 = scan.nextLine();
			break;
		case "6":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入QQ号码：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改QQ号码> ");
			userQQNum = scan.nextLine();
			break;
		case "7":
			refresh();
			System.out.println("     修改个人信息");
			System.out.println("--------------------\n");
			System.out.println("请输入所在地：");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改所在地> ");
			userLocation = scan.nextLine();
			break;
		case "0":
			initUserInfo(scan);
			break;
		}

		// 将用户信息写入文件
		Tools.WriteToFile.writeFileByName("user_info",
				Tools.WriteToFile.KIND_USER_INFO);

		System.out.println("修改成功\n");
		showUserInfo();
		System.out.println("选项：");
		System.out.println("   1.继续修改");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\个人信息\\修改个人信息> ");
	}

	// 导入通讯录
	private void importAdd(Scanner scan) {
		refresh(1);
		System.out.println("      导入通讯录");
		System.out.println("--------------------\n");
		System.out.println("1)导入分组信息：");
		System.out.println("   请输入文件名[Group.txt]：");
		System.out.print(userName + "@主菜单\\导入通讯录\\导入分组信息> ");
		String groupFileName = scan.nextLine();

		// ///////////// 测试用/////////////////////////////////////////////
		groupFileName = "Group.txt";
		// ////////////////////////////////////////////////////////////////

		while (!fileIsExist(groupFileName)) {
			System.out.println("   文件名输入有误，请重新输入");
			System.out.print(userName + "@主菜单\\导入通讯录\\导入分组信息> ");
			groupFileName = scan.nextLine();
		}

		Tools.ReadFromFile.readFileByLines(groupFileName,
				Tools.ReadFromFile.KIND_GROUP);
		refresh();
		System.out.println("正在导入分组信息，请稍后...");
		refresh();
		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("\n导入成功...\n");

		while (true) {
			System.out.println("\n      导入通讯录");
			System.out.println("--------------------\n");
			System.out.println("2)导入联系人信息：");
			System.out.println("   请输入文件名[Contacts.txt]：");
			System.out.print(userName + "@主菜单\\导入通讯录\\导入联系人> ");
			String contactsFileName = scan.nextLine();

			// ///////////// 测试用/////////////////////////////////////////////
			contactsFileName = "Contacts.txt";
			// ////////////////////////////////////////////////////////////////

			while (!fileIsExist(contactsFileName)) {
				System.out.println("   文件名输入有误，请重新输入");
				System.out.print(userName + "@主菜单\\导入通讯录\\导入联系人> ");
				contactsFileName = scan.nextLine();
			}

			Tools.ReadFromFile.readFileByLines(contactsFileName,
					Tools.ReadFromFile.KIND_CONTACTS);
			refresh();
			System.out.println("正在导入联系人信息，请稍后...");
			refresh();
			Iterator<Entry<Integer, People>> iter2 = contacts.entrySet()
					.iterator();
			while (iter2.hasNext()) {
				Map.Entry<Integer, People> entry = (Map.Entry<Integer, People>) iter2
						.next();
				System.out.println(((People) entry.getValue()).toString());
			}

			System.out.println("导入成功...\n");

			System.out.println("\n      导入通讯录");
			System.out.println("--------------------\n");
			System.out.println("选项：");
			System.out.println("   1.继续导入联系人");
			System.out.println("   2.返回主菜单");
			System.out.print(userName + "@主菜单\\导入通讯录> ");

			String key = scan.nextLine();

			if (key.equals("1")) {
				continue;
			} else if (key.equals("2")) {
				refresh();
				break;
			} else {
				break;
			}
		}
		// refresh();
	}

	// 判断一个文件是否存在
	private boolean fileIsExist(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
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

	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		client.run();
	}

}
