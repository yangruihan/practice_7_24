package Friends;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

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

	public String searchSecondContent = null;

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
				Tools.ReadFromFile.readFileByLines("Group.txt",
						Tools.ReadFromFile.KIND_GROUP);
				Tools.ReadFromFile.readFileByLines("Contacts.txt",
						Tools.ReadFromFile.KIND_CONTACTS);

				refresh();
				System.out
						.print("正在从默认文件(Group.txt, Contacts.txt)中导入数据，请稍等...");
				Thread.sleep(400);
				refresh();
				System.out.print("导入成功");
				Thread.sleep(200);
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
		System.out.print("手机号码 2(没有填“/”)> ");
		userPhoneNum2 = scan.nextLine();
		System.out.print("QQ号码(没有填“/”)> ");
		userQQNum = scan.nextLine();
		System.out.print("所在地> ");
		userLocation = scan.nextLine();
		System.out.println();
		System.out.println("数据添加成功!");
	}

	private void initConInfo(Scanner scan, int key) {
		System.out.print("姓名> ");
		contacts.get(key).setName(scan.nextLine());
		System.out.print("性别> ");
		contacts.get(key).setGender(scan.nextLine());
		System.out.print("生日[xxxx-xx-xx]> ");
		contacts.get(key).setBirthday(scan.nextLine());
		System.out.print("手机号码 1> ");
		contacts.get(key).setPhoneNum1(scan.nextLine());
		System.out.print("手机号码 2(没有填“/”)> ");
		contacts.get(key).setPhoneNum2(scan.nextLine());
		System.out.print("QQ号码(没有填“/”)> ");
		contacts.get(key).setQQNum(scan.nextLine());
		System.out.print("所在地> ");
		contacts.get(key).setLocation(scan.nextLine());

		System.out.println("请选择分组");

		Iterator<Entry<Integer, String>> iter2 = group.entrySet().iterator();
		while (iter2.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter2
					.next();
			System.out
					.println("   " + entry.getKey() + ": " + entry.getValue());
		}

		System.out.print("分组> ");
		contacts.get(key).setGroup(Integer.parseInt(scan.nextLine()));
		System.out.println();
		System.out.println("数据添加成功!");
	}

	// 显示用户信息
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

		// 查找联系人
		searchCont(scan, key, 1);
	}

	private void searchCont(Scanner scan, String key, int times) {
		System.out.println("      查找联系人");
		System.out.println("--------------------\n");

		int[] IDary = new int[contacts.size()];
		int resultNumber = 0;
		switch (key) {
		case "1":
			resultNumber = searchConByID(scan, IDary, times);
			break;
		case "2":
			resultNumber = searchConByGroup(scan, IDary, times);
			break;
		case "3":
			resultNumber = searchConByName(scan, IDary, times);
			break;
		default:
			return;
		}

		if (resultNumber == 0) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("   未能找到相关联系人");
		}

		System.out.println("\n共 " + resultNumber + " 个结果");
		System.out.println("\n选项：");
		System.out.println("   1.查看详细信息");
		System.out.println("   2.修改查询结果信息");
		System.out.println("   9.继续查找");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\查找联系人\\查找结果> ");
		String optkey = scan.nextLine();
		if (optkey.equals("9")) {
			optSearchCon(scan);
		} else if (optkey.equals("2")) {
			// 修改查询结果信息
			changeResultInfo(scan, key, times, IDary, resultNumber);
		} else if (optkey.equals("1")) {
			refresh();
			for (int i = 0; i < resultNumber; i++) {
				System.out.println("         第 " + (i + 1) + " 个结果");
				System.out
						.println(((People) contacts.get(IDary[i])).toString());
			}
			System.out.println("\n共 " + resultNumber + " 个结果");
			System.out.println("\n选项：");
			System.out.println("   1.修改查询结果信息");
			System.out.println("   2.继续查找");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\查找联系人\\查找结果\\详细信息> ");
			String dShowUserInfo = scan.nextLine();
			if (dShowUserInfo.equals("2")) {
				optSearchCon(scan);
			} else if (dShowUserInfo.equals("1")) {
				// 修改查询结果信息
				changeResultInfo(scan, key, times, IDary, resultNumber);
			} else {

			} // end of 1 2 0

		} else {

		}// key 1 2 9
	}

	// 修改查询结果信息
	private void changeResultInfo(Scanner scan, String key, int times,
			int[] IDary, int resultNumber) {
		if (resultNumber > 1) {
			refresh();
			System.out.println("    修改联系人信息");
			System.out.println("--------------------\n");
			System.out.println("查询结果：");
			for (int i = 0; i < resultNumber; i++) {
				System.out.println("   " + (i + 1) + "."
						+ ((People) contacts.get(IDary[i])).getName() + " "
						+ ((People) contacts.get(IDary[i])).getGender() + " "
						+ ((People) contacts.get(IDary[i])).getPhoneNum1()
						+ " "
						+ ((People) contacts.get(IDary[i])).getGroupName());
			}
			System.out.println("\n请选择需要修改的联系人编号[1,2...]\n");
			System.out.print(userName + "@主菜单\\查找联系人\\查找结果\\修改联系人信息> ");
			String changeContactNumber = scan.nextLine();
			int changeNumber = IDary[Integer.parseInt(changeContactNumber) - 1];

			changeContactInfo(scan, changeNumber, "@主菜单\\查找联系人\\查找结果\\修改联系人信息");
			changeContactNumber = scan.nextLine();
			while (changeContactNumber.equals("1")) {
				changeContactInfo(scan, changeNumber,
						"@主菜单\\查找联系人\\查找结果\\修改联系人信息");
				changeContactNumber = scan.nextLine();
			}
			if (changeContactNumber.equals("0")) {
				searchCont(scan, key, (times + 1));
			}
		} else {
			// 只有一个查询结果
			refresh();
			changeContactInfo(scan, IDary[0], "@主菜单\\查找联系人\\查找结果\\修改联系人信息");
			String changeContactNumber = scan.nextLine();
			while (changeContactNumber.equals("1")) {
				changeContactInfo(scan, IDary[0], "@主菜单\\查找联系人\\查找结果\\修改联系人信息");
				changeContactNumber = scan.nextLine();
			}
			if (changeContactNumber.equals("0")) {
				searchCont(scan, key, (times + 1));
			}
		}
	}

	// 通过姓名查找
	private int searchConByName(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人姓名\n");
			System.out.print(userName + "@主菜单\\查找联系人\\姓名查找> ");
			searchSecondContent = scan.nextLine();
		}
		int i = 0;
		refresh();

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		Iterator<Entry<Integer, People>> iter = contacts.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, People> entry = iter.next();
			if (((People) entry.getValue()).getName().equals(
					searchSecondContent)) {
				System.out.println("   " + (i + 1) + "."
						+ ((People) entry.getValue()).getName() + " "
						+ ((People) entry.getValue()).getGender() + " "
						+ ((People) entry.getValue()).getPhoneNum1() + " "
						+ ((People) entry.getValue()).getGroupName());
				IDary[i++] = entry.getKey();
			}
		}
		return i;
	}

	// 依据群组查找
	private int searchConByGroup(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请选择分组");

			Iterator<Entry<Integer, String>> iter2 = group.entrySet()
					.iterator();
			while (iter2.hasNext()) {
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter2
						.next();
				System.out.println("   " + entry.getKey() + ": "
						+ entry.getValue());
			}

			System.out.println();
			System.out.print(userName + "@主菜单\\查找联系人\\分组查找> ");
			searchSecondContent = scan.nextLine();
		}

		int i = 0;

		refresh();

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			if (String.valueOf(entry.getKey()).equals(searchSecondContent)) {
				System.out.println("         " + entry.getValue());
				System.out.println("------------------------");

				Iterator<Entry<Integer, People>> tempIter = contacts.entrySet()
						.iterator();
				while (tempIter.hasNext()) {
					Map.Entry<Integer, People> tempEntry = (Map.Entry<Integer, People>) tempIter
							.next();
					if (tempEntry.getValue().getGroup() == entry.getKey()) {
						System.out.println("   "
								+ ((People) tempEntry.getValue()).getName()
								+ " "
								+ ((People) tempEntry.getValue()).getGender()
								+ " "
								+ ((People) tempEntry.getValue())
										.getPhoneNum1());
						IDary[i++] = tempEntry.getKey();
					}
				}
			}
		}
		return i;
	}

	// 依据ID查找
	private int searchConByID(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人编号\n");
			System.out.print(userName + "@主菜单\\查找联系人\\编号查找> ");
			searchSecondContent = scan.nextLine();
		}
		int i = 0;
		refresh();

		People temp = contacts.get(Integer.parseInt(searchSecondContent));

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");
		System.out.println();
		System.out.println("   " + (i + 1) + "." + temp.getName() + " "
				+ temp.getGender() + " " + temp.getPhoneNum1() + " "
				+ temp.getGroupName());
		IDary[i++] = Integer.parseInt(searchSecondContent);

		// Iterator<Entry<Integer, People>> iter =
		// contacts.entrySet().iterator();
		// while (iter.hasNext()) {
		// Entry<Integer, People> entry = iter.next();
		// if (String.valueOf(entry.getKey()).equals(searchSecondContent)) {
		// System.out.println("      查找联系人");
		// System.out.println("--------------------\n");
		// System.out.println("查找结果：");
		// System.out.println("   " + (i + 1) + "."
		// + ((People) entry.getValue()).getName() + " "
		// + ((People) entry.getValue()).getGender() + " "
		// + ((People) entry.getValue()).getPhoneNum1() + " "
		// + ((People) entry.getValue()).getGroupName());
		// IDary[i++] = entry.getKey();
		// break;
		// }
		// }
		return i;
	}

	// 显示联系人选项
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
		System.out.println("   2.添加联系人");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\显示通讯录> ");
		String key = scan.nextLine();
		// 显示详细列表
		if (key.equals("1")) {
			refresh();
			showConByGroupD();
			System.out.println("选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示通讯录\\显示详细信息> ");
			scan.nextLine();
		} else if (key.equals("2")) {
			// 添加联系人
			addContact(scan);
		} else {

		}
	}

	// 添加联系人
	private void addContact(Scanner scan) {
		refresh();
		System.out.println("      添加联系人");
		System.out.println("--------------------\n");
		System.out.println("1.分组(从下列中选取一项，填序号)\n");
		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println("  " + entry.getKey() + ") " + entry.getValue());
		}
		System.out.println("\n2.姓名");
		System.out.println("3.性别");
		System.out.println("4.生日[xxxx-xx-xx]");
		System.out.println("5.手机号码 1");
		System.out.println("6.手机号码 2(没有填“/”)");
		System.out.println("7.QQ号码(没有填“/”)");
		System.out.println("8.所在地\n");
		System.out.println("\n请键入上述内容，每项用空格分开\n");
		System.out.print(userName + "@主菜单\\显示通讯录\\添加联系人> ");
		String str = scan.nextLine();
		String arg[] = str.split(" ");

		refresh();
		System.out.println("      添加联系人");
		System.out.println("--------------------\n");
		System.out.println("确定要添加吗？\n");
		System.out.println("选项：");
		System.out.println("   1.确定添加");
		System.out.println("   0.取消添加\n");
		System.out.print(userName + "@主菜单\\显示通讯录\\添加联系人> ");

		str = scan.nextLine();
		if (str.equals("0")) {

		} else if (str.equals("1")) {
			while (arg.length != 8) {
				refresh();
				System.out.println("输入错误\n");
				System.out.println("      添加联系人");
				System.out.println("--------------------\n");
				System.out.println("1.分组(从下列中选取一项，填序号)\n");
				Iterator<Entry<Integer, String>> iter3 = group.entrySet()
						.iterator();
				while (iter3.hasNext()) {
					Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter3
							.next();
					System.out.println("  " + entry.getKey() + ") "
							+ entry.getValue());
				}
				System.out.println("\n2.姓名");
				System.out.println("3.性别");
				System.out.println("4.生日[xxxx-xx-xx]");
				System.out.println("5.手机号码 1");
				System.out.println("6.手机号码 2(没有填“/”)");
				System.out.println("7.QQ号码(没有填“/”)");
				System.out.println("8.所在地\n");
				System.out.println("\n请键入上述内容，每项用空格分开\n");
				System.out.print(userName + "@主菜单\\显示通讯录\\添加联系人> ");
				str = scan.nextLine();
			}
			if (arg.length == 8) {
				String date[] = arg[3].split("-");
				People newPeople = new People(Integer.parseInt(arg[0]), arg[1],
						arg[2], new Date(Integer.parseInt(date[0]),
								Integer.parseInt(date[1]),
								Integer.parseInt(date[2])), arg[4], arg[5],
						arg[6], arg[7]);
				contacts.put(newPeople.getIDNumber(), newPeople);
				refresh();

				System.out.println("添加成功\n");
				System.out.println("      新联系人");
				System.out.println(contacts.get(newPeople.getIDNumber())
						.toString());
				System.out.println("选项：");
				System.out.println("   0.返回主菜单\n");
				System.out.print(userName + "@主菜单\\显示通讯录\\添加联系人> ");
				scan.nextLine();
			}
		}
	}

	// 粗略分组显示联系人
	@SuppressWarnings("unchecked")
	private void showConByGroupR() {
		System.out.println("             通讯录");
		System.out.println("------------------------------\n\n");

		HashMap<Integer, People> temp = (HashMap<Integer, People>) contacts
				.clone();

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
					System.out.println("    "
							+ ((People) tempEntry.getValue()).getName() + "   "
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

		HashMap<Integer, People> temp1 = (HashMap<Integer, People>) contacts
				.clone();

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
		System.out.print("系统正在退出中，请稍等...");
		Tools.WriteToFile.writeFileByName("Contacts.txt",
				Tools.WriteToFile.KIND_CON_INFO);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		refresh();
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

	// 更改用户信息
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
		System.out.println("   9.全部重置\n");
		System.out.println("   0.取消修改\n");
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
		case "9":
			initUserInfo(scan);
			break;
		case "0":
			refresh();
			System.out.println("修改取消\n");
			showUserInfo();
			System.out.println("选项：");
			System.out.println("   1.继续修改");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息> ");
			return;
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

	private void changeContactInfo(Scanner scan, int key, String path) {
		String pressKey;
		refresh();
		System.out.println("    修改联系人信息");
		System.out.println("--------------------\n");
		System.out.println("修改选项：");
		System.out.println("   1.姓名");
		System.out.println("   2.性别");
		System.out.println("   3.生日");
		System.out.println("   4.电话号码 1");
		System.out.println("   5.电话号码 2");
		System.out.println("   6.QQ号码");
		System.out.println("   7.所在地");
		System.out.println("   8.关系");
		System.out.println("   9.全部重置\n");
		System.out.println("   0.取消修改\n");
		System.out.print(userName + path + "> ");
		pressKey = scan.nextLine();
		refresh();
		System.out.println("    修改联系人信息");
		System.out.println("--------------------\n");
		switch (pressKey) {
		case "1":
			System.out.println("请输入姓名：\n");
			System.out.print(userName + path + "\\修改姓名> ");
			contacts.get(key).setName(scan.nextLine());
			break;
		case "2":
			System.out.println("请输入性别：\n");
			System.out.print(userName + path + "\\修改性别> ");
			contacts.get(key).setGender(scan.nextLine());
			break;
		case "3":
			System.out.println("请输入生日[xxxx-xx-xx]：\n");
			System.out.print(userName + path + "\\修改生日> ");
			contacts.get(key).setBirthday(scan.nextLine());
			break;
		case "4":
			System.out.println("请输入手机号码：\n");
			System.out.print(userName + path + "\\修改手机号码 1> ");
			contacts.get(key).setPhoneNum1(scan.nextLine());
			break;
		case "5":
			System.out.println("请输入手机号码：\n");
			System.out.print(userName + path + "\\修改手机号码 2> ");
			contacts.get(key).setPhoneNum2(scan.nextLine());
			break;
		case "6":
			System.out.println("请输入QQ号码：\n");
			System.out.print(userName + path + "\\修改QQ号码> ");
			contacts.get(key).setQQNum(scan.nextLine());
			break;
		case "7":
			System.out.println("请输入所在地：\n");
			System.out.print(userName + path + "\\修改所在地> ");
			contacts.get(key).setLocation(scan.nextLine());
			break;
		case "8":
			System.out.println("请输入群组：\n");

			Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
						.next();
				System.out.println("   " + entry.getKey() + ": "
						+ entry.getValue());
			}
			System.out.println();
			System.out.print(userName + path + "\\修改群组> ");
			contacts.get(key).setGroup(Integer.parseInt(scan.nextLine()));
			break;
		case "9":
			initConInfo(scan, key);
			break;
		default:
			refresh();
			System.out.println("修改取消\n");
			System.out.println(contacts.get(key).toString());
			System.out.println("选项：");
			System.out.println("   1.继续修改");
			System.out.println("   0.返回查询结果\n");
			System.out.print(userName + "@主菜单\\修改联系人信息> ");
			return;
		}

		refresh();
		System.out.println("修改成功\n");
		System.out.println(contacts.get(key).toString());
		System.out.println("选项：");
		System.out.println("   1.继续修改");
		System.out.println("   0.返回查询结果\n");
		System.out.print(userName + "@主菜单\\修改联系人信息> ");
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
		refresh(30);
	}

	private void refresh(int times) {
		for (int i = 0; i < times; i++) {
			System.out.println("\n");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		client.run();
	}

}
