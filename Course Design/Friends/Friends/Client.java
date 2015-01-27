package Friends;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Client implements Runnable {

	// 用来存放分组信息的hashmap
	public static HashMap<Integer, String> group = null;

	// 用来存放联系人的hashmap
	public static HashMap<Integer, People> contacts = null;

	// 用来存放各种不同类型的hashmap
	public HashMap<String, LinkList> NameMap = null;
	public HashMap<String, LinkList> NamePinyinMap = null;
	public HashMap<String, LinkList> NameHeadCharMap = null;
	public HashMap<String, LinkList> BirthMap = null;
	public HashMap<String, LinkList> Phone1Map = null;
	public HashMap<String, LinkList> Phone2Map = null;
	public HashMap<String, LinkList> QQNumMap = null;
	public HashMap<String, LinkList> LocationMap = null;
	public HashMap<String, LinkList> GenderMap = null;

	// 类型
	public final String KIND_NAME = "Name";
	public final String KIND_GROUP = "Group";
	public final String KIND_GENDER = "Gender";
	public final String KIND_ID = "ID";
	public final String KIND_PINYIN = "NamePinyin";
	public final String KIND_HEADCHAR = "NameHeadChar";
	public final String KIND_PHONENUM1 = "PhoneNum1";
	public final String KIND_PHONENUM2 = "PhoneNum2";
	public final String KIND_QQNUM = "QQNum";
	public final String KIND_LOCATION = "Location";
	public final String KIND_GROUPNAME = "GroupName";
	public final String KIND_BIRTH = "Birth";

	// 用户信息
	public static String userName;
	public static String userGender;
	public static String userBirth;
	public static String userPhoneNum;
	public static String userPhoneNum2;
	public static String userQQNum;
	public static String userLocation;

	public String searchSecondContent = null;

	// 写入写出的群组文件名及联系人文件名
	public final static String groupFileName = "Group.txt";
	public final static String contactsFileName = "Contacts.txt";
	public final static String userInfoFileName = "user_info.lib";

	public Client() {

	}

	// 多线程任务
	@Override
	public void run() {
		// 如果没有初始化
		if (NameMap == null || NamePinyinMap == null || NameHeadCharMap == null
				|| BirthMap == null || Phone1Map == null || Phone2Map == null
				|| QQNumMap == null || LocationMap == null || GenderMap == null) {
			// 初始化
			initHashMap();
		}

		// 处理姓名hashmap
		getHashMap();
	}

	// 得到各类hashmap信息
	private void getHashMap() {
		String str;
		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_NAME);
			if (NameMap.get(str) == null) {
				LinkList nameList = new LinkList();
				nameList.addNode(i);
				NameMap.put(str, nameList);
			} else {
				NameMap.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_PINYIN);
			if (NamePinyinMap.get(str) == null) {
				LinkList namePinyinList = new LinkList();
				namePinyinList.addNode(i);
				NamePinyinMap.put(str, namePinyinList);
			} else {
				NamePinyinMap.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_HEADCHAR);
			if (NameHeadCharMap.get(str) == null) {
				LinkList nameHeadCharList = new LinkList();
				nameHeadCharList.addNode(i);
				NameHeadCharMap.put(str, nameHeadCharList);
			} else {
				NameHeadCharMap.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_BIRTH);
			if (BirthMap.get(str) == null) {
				LinkList birthList = new LinkList();
				birthList.addNode(i);
				BirthMap.put(str, birthList);
			} else {
				BirthMap.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_PHONENUM1);
			if (Phone1Map.get(str) == null) {
				LinkList phone1List = new LinkList();
				phone1List.addNode(i);
				Phone1Map.put(str, phone1List);
			} else {
				Phone1Map.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_PHONENUM2);
			if (Phone2Map.get(str) == null) {
				LinkList phone2List = new LinkList();
				phone2List.addNode(i);
				Phone2Map.put(str, phone2List);
			} else {
				Phone2Map.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_QQNUM);
			if (QQNumMap.get(str) == null) {
				LinkList QQNumList = new LinkList();
				QQNumList.addNode(i);
				QQNumMap.put(str, QQNumList);
			} else {
				QQNumMap.get(str).addNode(i);
			}
		}

		for (int i = 1; i <= contacts.size(); i++) {
			str = getItem(i, KIND_LOCATION);
			if (LocationMap.get(str) == null) {
				LinkList locationList = new LinkList();
				locationList.addNode(i);
				LocationMap.put(str, locationList);
			} else {
				LocationMap.get(str).addNode(i);
			}
		}
	}

	// 初始化hashmap
	private void initHashMap() {
		NameMap = new HashMap<String, LinkList>();
		NamePinyinMap = new HashMap<String, LinkList>();
		NameHeadCharMap = new HashMap<String, LinkList>();
		BirthMap = new HashMap<String, LinkList>();
		Phone1Map = new HashMap<String, LinkList>();
		Phone2Map = new HashMap<String, LinkList>();
		QQNumMap = new HashMap<String, LinkList>();
		LocationMap = new HashMap<String, LinkList>();
		GenderMap = new HashMap<String, LinkList>();
	}

	// /////////////////////////////////////////////////////////////////////////////
	public void go() throws InterruptedException, IOException {
		Scanner scan = new Scanner(System.in);

		try {
			refresh();
			System.out.println("*************************************");
			System.out.println("****** 欢 迎 使 用 个 人 通 讯 录 ******");
			System.out.println("*************************************\n");

			// 初始化客户端
			initClient(scan);

			Thread thread = new Thread(this);
			thread.start();

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

	private void initClient(Scanner scan) throws InterruptedException,
			IOException {

		initHashMap();

		if (!fileIsExist(userInfoFileName)) {

			System.out.println("首先，完善自己的个人资料:");

			// 初始化用户信息
			initUserInfo(scan);

			if (!fileIsExist(userInfoFileName)) {
				File file = new File(userInfoFileName);
				file.createNewFile();
			}

			Tools.WriteToFile.writeFileByName(userInfoFileName,
					Tools.WriteToFile.KIND_USER_INFO);

			refresh();
		} else {
			Tools.ReadFromFile.readFileByLines(userInfoFileName,
					Tools.ReadFromFile.KIND_USER_INFO);
		}

		// 显示个人信息
		showUserInfo();

		System.out.print("进入主菜单(yes/no)> ");
		String yesString = scan.nextLine();
		if (yesString.equals("no")) {
			optExitSys(scan);
		} else {
			if (!fileIsExist(groupFileName)) {
				File file = new File(groupFileName);
				file.createNewFile();
			}

			if (!fileIsExist(contactsFileName)) {
				File file = new File(contactsFileName);
				file.createNewFile();
			}

			// 默认从 Group.txt && Contacts.txt 导入数据
			if (fileIsExist(groupFileName) && fileIsExist(contactsFileName)) {
				if (group == null) {
					group = new HashMap<Integer, String>();
				}
				if (contacts == null) {
					contacts = new HashMap<Integer, People>();
				}
				refresh();
				System.out
						.print("正在从默认文件(Group.txt, Contacts.txt)中导入数据，请稍等...");
				Tools.ReadFromFile.readFileByLines(groupFileName,
						Tools.ReadFromFile.KIND_GROUP);
				Tools.ReadFromFile.readFileByLines(contactsFileName,
						Tools.ReadFromFile.KIND_CONTACTS);

				refresh();
				System.out.println("*********************");
				System.out.println("***** 导 入 成 功 *****");
				System.out.println("*********************");
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
		System.out.println("*********************");
		System.out.println("***** 重 置 成 功 *****");
		System.out.println("*********************");
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
		System.out.println("*********************");
		System.out.println("***** 重 置 成 功 *****");
		System.out.println("*********************");
	}

	// 显示用户信息
	private void showUserInfo() {
		System.out.println("-------------- 个人信息 --------------\n");
		System.out.println("--- 姓名：" + userName + " ---");
		System.out.println("--- 性别：" + userGender + " ---");
		System.out.println("--- 生日：" + userBirth + " ---");
		System.out.println("--- 手机号码 1：" + userPhoneNum + " ---");
		System.out.println("--- 手机号码 2：" + userPhoneNum2 + " ---");
		System.out.println("--- QQ号码：" + userQQNum + " ---");
		System.out.println("--- 所在地：" + userLocation + " ---");
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
			System.out.println("   2.导入联系人");
			System.out.println("   3.显示联系人");
			System.out.println("   4.查找联系人");
			System.out.println("   5.重置通讯录");
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
			case "5":
				optReSetCon(scan);
				break;
			case "0":
				optExitSys(scan);
				break;
			default:
				refresh();
				break;
			}// end of switch

		}
	}

	// /////////////////////////////////////////////////////////////////////////////

	// 重置通讯录选项
	private void optReSetCon(Scanner scan) {
		refresh();
		System.out.println("      重置通讯录");
		System.out.println("--------------------\n");
		System.out.println("警告：");
		System.out.println("   这将会删除通讯录中的所有信息，此操作不可逆\n");
		System.out.println("选项：");
		System.out.println("   1.确定重置");
		System.out.println("   0.取消重置\n");
		System.out.print(userName + "@主菜单\\重置通讯录> ");
		String pressKey = scan.nextLine();
		if (pressKey.equals("1")) {
			// 清空联系人
			clearCon();
		}
	}

	// 清空联系人
	private void clearCon() {
		contacts.clear();
		group.clear();
		NameMap.clear();
		NamePinyinMap.clear();
		NameHeadCharMap.clear();
		BirthMap.clear();
		Phone1Map.clear();
		Phone2Map.clear();
		QQNumMap.clear();
		LocationMap.clear();
		GenderMap.clear();
		People.setPEOPLE_NUMBER(0);
		Tools.WriteToFile.writeFileByName(contactsFileName,
				Tools.WriteToFile.KIND_CON_INFO);
		Tools.WriteToFile.writeFileByName(groupFileName,
				Tools.WriteToFile.KIND_GROUP_INFO);
	}

	// 查找联系人选项
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

	// 查找联系人
	private void searchCont(Scanner scan, String key, int times) {
		System.out.println("      查找联系人");
		System.out.println("--------------------\n");

		int[] IDary = new int[contacts.size()];
		int resultNumber = 0;
		switch (key) {

		// 根据ID查找
		case "1":
			resultNumber = searchConByID(scan, IDary, times);
			break;

		// 根据群组查找 可优化
		case "2":
			resultNumber = searchConByGroup(scan, IDary, times);
			break;

		// 根据姓名查找
		case "3":
			resultNumber = searchConByName(scan, IDary, times);
			break;

		// 根据姓名拼音查找
		case "4":
			resultNumber = searchConByPinyin(scan, IDary, times);
			break;

		// 根据手机号码查找 目前只设计了根据一个手机号 还需修改
		case "5":
			resultNumber = searchConByPhone(scan, IDary, times);
			break;

		// 根据QQ号码查找
		case "6":
			resultNumber = searchConByQQ(scan, IDary, times);
			break;

		// 根据地理位置查找
		case "7":
			resultNumber = searchConByLoc(scan, IDary, times);
			break;

		// 根据关键词查找
		case "9":
			resultNumber = searchConByKey(scan, IDary, times);
			break;
		default:
			return;
		}

		if (resultNumber == 0) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("   未能找到相关联系人");
			System.out.println("\n选项：");
			System.out.println("   1.继续查找");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\查找联系人\\查找结果> ");
			String optkey = scan.nextLine();
			if (optkey.equals("1")) {
				optSearchCon(scan);
			} else {
				return;
			}
		} else if (resultNumber == -1) {
			return;
		} else if (resultNumber != 0) {
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
					System.out.println(((People) contacts.get(IDary[i]))
							.toString());
				}
				System.out.println("\n共 " + resultNumber + " 个结果");
				System.out.println("\n选项：");
				System.out.println("   1.修改查询结果信息");
				System.out.println("   9.继续查找");
				System.out.println("   0.返回主菜单\n");
				System.out.print(userName + "@主菜单\\查找联系人\\查找结果\\详细信息> ");
				String dShowUserInfo = scan.nextLine();
				if (dShowUserInfo.equals("9")) {
					optSearchCon(scan);
				} else if (dShowUserInfo.equals("1")) {
					// 修改查询结果信息
					changeResultInfo(scan, key, times, IDary, resultNumber);
				} else {

				} // end of 1 2 0

			} else {

			}// key 1 2 9
		}
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

	// 通过关键词查找
	private int searchConByKey(Scanner scan, int[] IDary, int times) {

		return 0;
	}

	// 通过地理位置查找
	private int searchConByLoc(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人所在地\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\所在地查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		refresh();

		if (LocationMap.get(searchSecondContent) == null) {
			return 0;
		}

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		LocationMap.get(searchSecondContent).print();
		LocationMap.get(searchSecondContent).toArray(IDary);

		return LocationMap.get(searchSecondContent).getLength();
	}

	// 通过QQ号码查找
	private int searchConByQQ(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人QQ号码\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\QQ号码查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		refresh();

		if (QQNumMap.get(searchSecondContent) == null) {
			return 0;
		}

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		QQNumMap.get(searchSecondContent).print();
		QQNumMap.get(searchSecondContent).toArray(IDary);

		return QQNumMap.get(searchSecondContent).getLength();
	}

	// 通过手机号查找 暂时通过手机号码1
	private int searchConByPhone(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人手机号码\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\手机号码查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		refresh();

		if (Phone1Map.get(searchSecondContent) == null) {
			return 0;
		}

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		Phone1Map.get(searchSecondContent).print();
		Phone1Map.get(searchSecondContent).toArray(IDary);

		return Phone1Map.get(searchSecondContent).getLength();
	}

	// 通过拼音查找
	private int searchConByPinyin(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人姓名拼音\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\姓名查找(拼音)> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		refresh();

		if (NamePinyinMap.get(searchSecondContent) == null) {
			return 0;
		}

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		NamePinyinMap.get(searchSecondContent).print();
		NamePinyinMap.get(searchSecondContent).toArray(IDary);

		return NamePinyinMap.get(searchSecondContent).getLength();
	}

	// 通过姓名查找
	private int searchConByName(Scanner scan, int[] IDary, int times) {
		if (times == 1) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入联系人姓名\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\姓名查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		refresh();

		if (NameMap.get(searchSecondContent) == null) {
			return 0;
		}

		System.out.println("      查找联系人");
		System.out.println("--------------------\n");
		System.out.println("查找结果：");

		NameMap.get(searchSecondContent).print();
		NameMap.get(searchSecondContent).toArray(IDary);

		return NameMap.get(searchSecondContent).getLength();
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
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\分组查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}

		int i = 0;

		refresh();

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();

		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			if (String.valueOf(entry.getKey()).equals(searchSecondContent)) {
				System.out.println("              " + entry.getValue());
				System.out.println("-----------------------------------\n");

				Iterator<Entry<Integer, People>> tempIter = contacts.entrySet()
						.iterator();
				while (tempIter.hasNext()) {
					Map.Entry<Integer, People> tempEntry = (Map.Entry<Integer, People>) tempIter
							.next();
					if (tempEntry.getValue().getGroup() == entry.getKey()) {
						System.out.println("ID:"
								+ tempEntry.getKey()
								+ "\t"
								+ ((People) tempEntry.getValue()).getName()
								+ "\t"
								+ ((People) tempEntry.getValue()).getGender()
								+ "\t"
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
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\编号查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		int i = 0;
		refresh();

		while (!isNum(searchSecondContent)) {
			refresh();
			System.out.println("      查找联系人");
			System.out.println("--------------------\n");
			System.out.println("请输入正确的联系人编号(不能包含字母或标点)\n");
			System.out.println("   0.取消查找\n");
			System.out.print(userName + "@主菜单\\查找联系人\\编号查找> ");
			searchSecondContent = scan.nextLine();
			if (searchSecondContent.equals("0")) {
				return -1;
			}
		}
		
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

		return i;
	}

	// 显示联系人选项
	private void optShowCon(Scanner scan) {
		refresh();

		// 如果还未导入数据
		if (contacts.size() == 0) {
			System.out.println("      显示联系人");
			System.out.println("--------------------\n");
			System.out.println("通讯录无数据，请先导入联系人\n");
			System.out.println("选项：");
			System.out.println("   1.导入联系人");
			System.out.println("   0.返回主菜单\n");

			System.out.print(userName + "@主菜单\\显示联系人> ");

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
		System.out.println("   3.删除联系人");
		System.out.println("   4.添加分组");
		System.out.println("   5.删除分组");
		System.out.println("   0.返回主菜单\n");
		System.out.print(userName + "@主菜单\\显示联系人> ");
		String key = scan.nextLine();
		// 显示详细列表
		if (key.equals("1")) {
			refresh();
			showConByGroupD();
			System.out.println("选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示联系人\\显示详细信息> ");
			scan.nextLine();
		} else if (key.equals("2")) {
			// 添加联系人
			addContact(scan);
		} else if (key.equals("3")) {
			delContact(scan);
		} else if (key.equals("4")) {
			addGroup(scan);
		} else if (key.equals("5")) {
			delGroup(scan);
		} else {

		}
	}

	// 删除分组
	private void delGroup(Scanner scan) {
		refresh();
		System.out.println("       删除分组");
		System.out.println("--------------------\n");
		System.out.println("当前分组：\n");
		showGroup();
		System.out.println("\n请键入要删除的分组序号\n");
		System.out.println("   0.取消删除\n");
		System.out.print(userName + "@主菜单\\显示联系人\\删除分组> ");
		String groupID = scan.nextLine();
		if (groupID.equals("0")) {
			return;
		}
		refresh();
		System.out.println("       删除分组");
		System.out.println("--------------------\n");
		System.out.println("警告：");
		System.out.println("   这将删除整个分组，包括分组内的联系人\n");
		System.out.println("选项：");
		System.out.println("   1.确定删除");
		System.out.println("   0.取消删除\n");
		System.out.print(userName + "@主菜单\\显示联系人\\删除分组> ");
		String str = scan.nextLine();
		if (str.equals("1")) {
			group.remove(Integer.parseInt(groupID));
			removeContactByGroup(Integer.parseInt(groupID));
			refresh();
			System.out.println("*********************");
			System.out.println("***** 删 除 成 功 *****");
			System.out.println("*********************\n");
			System.out.println("       当前分组");
			System.out.println("--------------------\n");
			showGroup();
			System.out.println("\n选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示联系人\\删除分组> ");
			str = scan.nextLine();

		}
	}

	private void removeContactByGroup(int key) {
		for (int i = 1; i <= contacts.size(); i++) {
			if (getItem(i, KIND_GROUP).equals(String.valueOf(key))) {
				contacts.remove(i);
			}
		}
		initGroupAndContacts();
		Thread thread = new Thread(this);
		thread.start();
	}

	// 添加分组
	private void addGroup(Scanner scan) {
		refresh();
		System.out.println("       添加分组");
		System.out.println("--------------------\n");
		System.out.println("当前分组：\n");
		showGroup();
		System.out.println("\n请键入新添加的分组名\n");
		System.out.println("   0.取消添加\n");
		System.out.print(userName + "@主菜单\\显示联系人\\添加分组> ");
		String groupName = scan.nextLine();
		if (groupName.equals("0")) {
			return;
		}
		refresh();
		System.out.println("       添加分组");
		System.out.println("--------------------\n");
		System.out.println("选项：");
		System.out.println("   1.确定添加");
		System.out.println("   0.取消添加\n");
		System.out.print(userName + "@主菜单\\显示联系人\\添加分组> ");
		String str = scan.nextLine();
		if (str.equals("1")) {
			group.put(group.size() + 1, groupName);
			initGroupAndContacts();
			refresh();
			System.out.println("*********************");
			System.out.println("***** 添 加 成 功 *****");
			System.out.println("*********************\n");
			System.out.println("       当前分组");
			System.out.println("--------------------\n");
			showGroup();
			System.out.println("\n选项：");
			System.out.println("   1.移动联系人到该分组");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示联系人\\添加分组> ");
			str = scan.nextLine();

		} else {

		}
	}

	// 删除联系人
	private void delContact(Scanner scan) {
		refresh();
		System.out.println("      删除联系人");
		System.out.println("--------------------\n");
		// 分组显示联系人
		showConByGroupR();
		System.out.println("\n请选择要删除的联系人ID号\n");
		System.out.println("   0.取消删除\n");
		System.out.print(userName + "@主菜单\\显示联系人\\删除联系人> ");
		String delID = scan.nextLine();

		if (delID.equals("0")) {
			return;
		}

		refresh();
		System.out.println("      删除联系人");
		System.out.println("--------------------\n");
		System.out.println("选项：");
		System.out.println("   1.确定删除");
		System.out.println("   0.取消删除\n");
		System.out.print(userName + "@主菜单\\显示联系人\\删除联系人> ");

		String pressKey = scan.nextLine();

		if (pressKey.equals("1")) {
			contacts.remove(Integer.parseInt(delID));
			refresh();

			// 初始化群组和联系人
			initGroupAndContacts();

			System.out.println("*********************");
			System.out.println("***** 删 除 成 功 *****");
			System.out.println("*********************\n");

			// 分组显示联系人
			showConByGroupR();

			Thread thread = new Thread(this);
			thread.start();
			System.out.println("选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示联系人\\删除联系人> ");
			String pressKey2 = scan.nextLine();
			if (pressKey2.equals("0")) {

			} else {

			}// end of pressKey2

		} else {

		} // end of pressKey
	}

	// 初始化group 和 contacts
	private void initGroupAndContacts() {
		Tools.WriteToFile.writeFileByName(groupFileName,
				Tools.WriteToFile.KIND_GROUP_INFO);
		Tools.WriteToFile.writeFileByName(contactsFileName,
				Tools.WriteToFile.KIND_CON_INFO);
		group.clear();
		contacts.clear();
		People.setPEOPLE_NUMBER(0);
		Tools.ReadFromFile.readFileByLines(groupFileName,
				Tools.ReadFromFile.KIND_GROUP);
		Tools.ReadFromFile.readFileByLines(contactsFileName,
				Tools.ReadFromFile.KIND_CONTACTS);
	}

	// 添加联系人
	private void addContact(Scanner scan) {
		refresh();
		System.out.println("      添加联系人");
		System.out.println("--------------------\n");
		System.out.println("1.分组__*__(从下列中选取一项，填序号)\n");
		showGroup();

		System.out.println("\n2.姓名__*__");
		System.out.println("3.性别_____");
		System.out.println("4.生日[xxxx-xx-xx]_____");
		System.out.println("5.手机号码 1__*__");
		System.out.println("6.手机号码 2_____");
		System.out.println("7.QQ号码_____");
		System.out.println("8.所在地__*__\n");
		System.out.println("请键入上述内容，每项用空格分开\n注意：以上项中*项为必填项，其他项如为空则键入\"/\"\n");
		System.out.println("   0.取消添加\n");
		System.out.print(userName + "@主菜单\\显示联系人\\添加联系人> ");
		String str = scan.nextLine();

		if (str.equals("0")) {
			return;
		}

		refresh();
		System.out.println("      添加联系人");
		System.out.println("--------------------\n");
		System.out.println("确定要添加吗？\n");
		System.out.println("选项：");
		System.out.println("   1.确定添加");
		System.out.println("   0.取消添加\n");
		System.out.print(userName + "@主菜单\\显示联系人\\添加联系人> ");

		str = scan.nextLine();
		if (str.equals("0")) {

		} else if (str.equals("1")) {
			while (!infoIsRight(str)) {
				refresh();
				System.out.println("*********************");
				System.out.println("*****输 入 错 误 *****");
				System.out.println("*********************\n");
				System.out.println("      添加联系人");
				System.out.println("--------------------\n");
				System.out.println("1.分组__*__(从下列中选取一项，填序号)\n");
				showGroup();

				System.out.println("\n2.姓名__*__");
				System.out.println("3.性别_____");
				System.out.println("4.生日[xxxx-xx-xx]_____");
				System.out.println("5.手机号码 1__*__");
				System.out.println("6.手机号码 2_____");
				System.out.println("7.QQ号码_____");
				System.out.println("8.所在地__*__\n");
				System.out
						.println("请键入上述内容，每项用空格分开\n注意：以上项中*项为必填项，其他项如为空则键入\"/\"\n");
				System.out.print(userName + "@主菜单\\显示联系人\\添加联系人> ");
				System.out.println("   0.取消添加\n");
				str = scan.nextLine();
				if (str.equals("0")) {
					return;
				}
			}
			String arg[] = str.split(" ");

			String date[];
			if (arg[3].equals("/")) {
				date = new String[] { "0", "0", "0" };
			} else {
				date = arg[3].split("-");
			}

			People newPeople = new People(Integer.parseInt(arg[0]), arg[1],
					arg[2], new Date(Integer.parseInt(date[0]),
							Integer.parseInt(date[1]),
							Integer.parseInt(date[2])), arg[4], arg[5], arg[6],
					arg[7]);
			contacts.put(newPeople.getIDNumber(), newPeople);
			refresh();

			// 初始化hashmap
			Thread thread = new Thread(this);
			thread.start();

			System.out.println("*********************");
			System.out.println("***** 添 加 成 功 *****");
			System.out.println("*********************\n");
			System.out.println("        新联系人");
			System.out
					.println(contacts.get(newPeople.getIDNumber()).toString());
			System.out.println("选项：");
			System.out.println("   0.返回主菜单\n");
			System.out.print(userName + "@主菜单\\显示联系人\\添加联系人> ");
			scan.nextLine();
		}
	}

	// 显示分组信息
	private void showGroup() {
		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println("  " + entry.getKey() + ") " + entry.getValue());
		}
	}

	// 判断用户输入信息是否正确
	private boolean infoIsRight(String info) {
		String ary[] = info.split(" ");
		if (ary.length != 8) {
			return false;
		}
		if (group.get(Integer.parseInt(ary[0])) == null) {
			return false;
		}
		if (!ary[2].equals("男") && !ary[2].equals("女") && !ary[2].equals("/")) {
			return false;
		}

		return true;
	}

	// 粗略分组显示联系人
	@SuppressWarnings("unchecked")
	private void showConByGroupR() {
		System.out.println("              通讯录");
		System.out.println("-----------------------------------\n\n");

		HashMap<Integer, People> temp = (HashMap<Integer, People>) contacts
				.clone();

		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		int[] record = new int[contacts.size()];
		int i = 0;

		while (iter.hasNext()) {
			i = 0;

			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println("              " + entry.getValue());
			System.out.println("-----------------------------------");

			Iterator<Entry<Integer, People>> tempIter = temp.entrySet()
					.iterator();
			while (tempIter.hasNext()) {
				Map.Entry<Integer, People> tempEntry = (Map.Entry<Integer, People>) tempIter
						.next();
				if (tempEntry.getValue().getGroup() == entry.getKey()) {
					System.out.println("ID:"
							+ ((People) tempEntry.getValue()).getIDNumber()
							+ "\t" + ((People) tempEntry.getValue()).getName()
							+ "\t"
							+ ((People) tempEntry.getValue()).getGender()
							+ "\t"
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
	private void optExitSys(Scanner scan) {
		refresh();
		System.out.print("系统正在退出中，请稍等...");
		if (contacts != null && group != null) {
			Tools.WriteToFile.writeFileByName(contactsFileName,
					Tools.WriteToFile.KIND_CON_INFO);
			Tools.WriteToFile.writeFileByName(groupFileName,
					Tools.WriteToFile.KIND_GROUP_INFO);
		}
		Tools.WriteToFile.writeFileByName(userInfoFileName,
				Tools.WriteToFile.KIND_USER_INFO);

		if (scan != null) {
			try {
				scan.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		refresh();
		System.out.println("*********************");
		System.out.println("***** 谢 谢 使 用 *****");
		System.out.print("*********************");
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
		System.out.println("      导入联系人");
		System.out.println("--------------------\n");
		System.out.println("选项：");
		System.out.println("   0.取消导入\n");
		System.out.println("1)导入分组信息：");
		System.out.println("   请输入文件名[Group.txt]：\n");
		System.out.print(userName + "@主菜单\\导入联系人\\导入分组信息> ");
		String str = scan.nextLine();
		if (str.equals("0")) {
			return;
		}
		// ///////////// 测试用/////////////////////////////////////////////
		// str = "Group.txt";
		// ////////////////////////////////////////////////////////////////

		while (!fileIsExist(str)) {
			refresh();
			System.out.println("      导入联系人");
			System.out.println("--------------------\n");
			System.out.println("选项：");
			System.out.println("   0.取消导入\n");
			System.out.println("文件名输入有误，请重新输入[Group.txt]\n");
			System.out.print(userName + "@主菜单\\导入联系人\\导入分组信息> ");
			str = scan.nextLine();
			if (str.equals("0")) {
				return;
			}
		}
		refresh();
		System.out.print("正在导入分组信息，请稍后...");
		Tools.ReadFromFile.readFileByLines(str, Tools.ReadFromFile.KIND_GROUP);
		refresh();
		Iterator<Entry<Integer, String>> iter = group.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) iter
					.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		System.out.println("\n*********************");
		System.out.println("***** 导 入 成 功 *****");
		System.out.println("*********************\n");

		while (true) {
			refresh();
			System.out.println("\n      导入联系人");
			System.out.println("--------------------\n");
			System.out.println("选项：");
			System.out.println("   0.取消导入\n");
			System.out.println("2)导入联系人信息：");
			System.out.println("   请输入文件名[Contacts.txt]：\n");
			System.out.print(userName + "@主菜单\\导入联系人\\导入联系人> ");
			str = scan.nextLine();
			if (str.equals("0")) {
				return;
			}

			// ///////////// 测试用/////////////////////////////////////////////
			// str = "Contacts.txt";
			// ////////////////////////////////////////////////////////////////

			while (!fileIsExist(str)) {
				refresh();
				System.out.println("\n      导入联系人");
				System.out.println("--------------------\n");
				System.out.println("选项：");
				System.out.println("   0.取消导入\n");
				System.out.println("文件名输入有误，请重新输入[Contacts.txt]\n");
				System.out.print(userName + "@主菜单\\导入联系人\\导入联系人> ");
				str = scan.nextLine();
				if (str.equals("0")) {
					return;
				}
			}

			refresh();
			System.out.print("正在导入联系人信息，请稍后...");
			Tools.ReadFromFile.readFileByLines(str,
					Tools.ReadFromFile.KIND_CONTACTS);

			refresh();
			showConByGroupR();

			Thread thread = new Thread(this);
			thread.start();

			// ///////////////////////////////////////////
			Tools.WriteToFile.writeFileByName(groupFileName,
					Tools.WriteToFile.KIND_GROUP_INFO);
			Tools.WriteToFile.writeFileByName(contactsFileName,
					Tools.WriteToFile.KIND_CON_INFO);
			// ///////////////////////////////////////////

			System.out.println("\n*********************");
			System.out.println("***** 导 入 成 功 *****");
			System.out.println("*********************\n");

			System.out.println("\n      导入联系人");
			System.out.println("--------------------\n");
			System.out.println("选项：");
			System.out.println("   1.继续导入联系人");
			System.out.println("   0.返回主菜单");
			System.out.print(userName + "@主菜单\\导入联系人> ");

			String key = scan.nextLine();

			if (key.equals("1")) {
				continue;
			} else if (key.equals("0")) {
				refresh();
				break;
			} else {
				break;
			}
		}
		// refresh();
	}

	// 显示用户信息选项
	private void optShowUserInfo(Scanner scan) {
		refresh();
		showUserInfo();
		System.out.println("选项：");
		System.out.println("   1.修改个人信息");
		System.out.println("   0.返回主菜单\n");
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
		} else {

		}// end of 1 0
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

		refresh();
		System.out.println("     修改个人信息");
		System.out.println("--------------------\n");
		switch (pressKey) {
		case "1":
			System.out.println("请输入姓名：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改姓名> ");
			userName = scan.nextLine();
			break;
		case "2":
			refresh();
			System.out.println("请输入性别：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改性别> ");
			userGender = scan.nextLine();
			break;
		case "3":
			refresh();
			System.out.println("请输入生日[xxxx-xx-xx]：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改生日> ");
			userBirth = scan.nextLine();
			break;
		case "4":
			refresh();
			System.out.println("请输入手机号码：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改手机号码 1> ");
			userPhoneNum = scan.nextLine();
			break;
		case "5":
			refresh();
			System.out.println("请输入手机号码：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改手机号码 2> ");
			userPhoneNum2 = scan.nextLine();
			break;
		case "6":
			refresh();
			System.out.println("请输入QQ号码：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改QQ号码> ");
			userQQNum = scan.nextLine();
			break;
		case "7":
			refresh();
			System.out.println("请输入所在地：\n");
			System.out.print(userName + "@主菜单\\个人信息\\修改个人信息\\修改所在地> ");
			userLocation = scan.nextLine();
			break;
		case "9":
			initUserInfo(scan);
			break;
		case "0":
			refresh();
			System.out.println("*********************");
			System.out.println("***** 修 改 取 消 *****");
			System.out.println("*********************\n");
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

		refresh();
		System.out.println("*********************");
		System.out.println("***** 修 改 成 功 *****");
		System.out.println("*********************\n");
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
			System.out.println("*********************");
			System.out.println("***** 修 改 取 消 *****");
			System.out.println("*********************\n");
			System.out.println(contacts.get(key).toString());
			System.out.println("选项：");
			System.out.println("   1.继续修改");
			System.out.println("   0.返回查询结果\n");
			System.out.print(userName + "@主菜单\\修改联系人信息> ");
			return;
		}

		refresh();
		System.out.println("*********************");
		System.out.println("***** 修 改 成 功 *****");
		System.out.println("*********************\n");
		System.out.println(contacts.get(key).toString());
		System.out.println("选项：");
		System.out.println("   1.继续修改");
		System.out.println("   0.返回查询结果\n");
		System.out.print(userName + "@主菜单\\修改联系人信息> ");

		// 修改对应的hashmap
		Thread thread = new Thread(this);
		thread.start();
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

	// 判断一个文件是否为空
	private boolean fileIsEmpty(String filename) {
		File file = new File(filename);
		if (file.length() == 0)
			return true;
		return false;
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

	// 通过key，得到联系人信息
	private String getItem(int key, String kind) {

		if (contacts == null || contacts.size() == 0) {
			return null;
		}

		switch (kind) {
		case "Name":
			return contacts.get(key).getName();
		case "Group":
			return String.valueOf(contacts.get(key).getGroup());
		case "Gender":
			return contacts.get(key).getGender();
		case "ID":
			return String.valueOf(contacts.get(key).getIDNumber());
		case "NamePinyin":
			return contacts.get(key).getNamePinyin();
		case "NameHeadChar":
			return contacts.get(key).getHeadChar();
		case "PhoneNum1":
			return contacts.get(key).getPhoneNum1();
		case "PhoneNum2":
			return contacts.get(key).getPhoneNum2();
		case "QQNum":
			return contacts.get(key).getQQNum();
		case "Location":
			return contacts.get(key).getLocation();
		case "GroupName":
			return contacts.get(key).getGroupName();
		case "Birth":
			return contacts.get(key).getBirthday().toString();
		default:
			return null;
		}
	}

	// 判断一个字符串是否能转换成一个数字
	private boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static void main(String[] args) throws InterruptedException,
			IOException {
		Client client = new Client();
		client.go();
	}
}
