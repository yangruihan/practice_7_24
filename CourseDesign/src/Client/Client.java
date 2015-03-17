package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import Tools.Tools;

/**
 * 客户端类，用来启动程序
 * 
 * @author 范勇
 * @version 1.0
 */
public class Client implements Runnable {

	private static String GROUP_FILE_NAME; // 分组文件名
	private static String CONT_FILE_NAME; // 联系人文件名

	// 此 HashMap通过<ID,People>的方式将ID号和People类一一对应起来
	public static HashMap<String, People> peoples = new HashMap<String, People>();
	// 此 HashMap通过<Num,String>的方式将Group号和Group文字一一对应起来
	public static HashMap<String, String> groups = new HashMap<String, String>();

	// 主函数
	public void work() {

		// 实例化一个Scanner 方便后面从控制台读数据
		Scanner scan = new Scanner(System.in);

		// 初始化
		init();

		// 刷新
		refresh();

		System.out.println("----------欢迎使用个人通讯录----------");
		System.out.println("\t\t\t\t--by 范勇\n");

		// 从电脑中导入文件
		importFile(scan, 1);

		// 程序主循环
		mainLoop(scan);

	}

	/**
	 * 程序初始化
	 */
	private void init() {
		// People 计数器清零
		People.setNUM_OF_PEOPLE(0);

		// 如果groups不存在，则创建一个实例
		if (groups == null) {
			groups = new HashMap<String, String>();
		}
		if (!groups.isEmpty()) {
			groups.clear();
		}

		// 如果peoples不存在，则创建一个实例
		if (peoples == null) {
			peoples = new HashMap<String, People>();
		}
		if (!peoples.isEmpty()) {
			peoples.clear();
		}
	}

	/**
	 * 程序主循环
	 * 
	 * @param scan
	 * @param userInput
	 * @return
	 */
	private void mainLoop(Scanner scan) {

		// 刷新
		refresh();

		// 接受用户输入的字符串
		String userInput = "";

		// 程序主循环，退出标记为当userInput为-1000时
		while (!userInput.equalsIgnoreCase("-1000")) {

			// 程序主界面
			System.out.println("-------------个人通讯录-------------");
			System.out.println("\t\t\t\t--by 范勇\n");
			System.out.println("选项：");
			System.out.println("  -1.导入文件(im)");
			System.out.println("  -2.显示信息(sh)");
			System.out.println("  -3.添加信息(ad)");
			System.out.println("  -4.删除信息(de)");
			System.out.println("  -5.查找联系人(fi)");
			System.out.println("  -9.关于(ab)");
			System.out.println("  -0.退出(ex)");
			System.out.print("\n请用户选择要执行的操作(输入编号或括号里的字母皆可)：");

			// 读入用户输入
			userInput = scan.next();

			// 判断用户输入，并根据输入执行不同的功能
			userInput = judgeUserInput(scan, userInput);

			// 刷新
			refresh();
		}
	}

	/**
	 * 根据用户的输入，执行不同的功能
	 * 
	 * @param scan
	 * @param userInput
	 */
	private String judgeUserInput(Scanner scan, String userInput) {
		// 当用户选择退出时，询问用户是否确定
		if (userInput.equalsIgnoreCase("0") || userInput.equalsIgnoreCase("ex")) {
			// 刷新
			refresh();
			System.out.print("确定要退出吗[Y/N]: ");
			// 读取用户输入
			String tempInput = scan.next();
			// 如果用户确定退出，则将userInput标记为-1000
			// 否则无需更改
			if (tempInput.equalsIgnoreCase("y")) {
				
				// 保存
				this.run();
				
				return "-1000";
			}
		}

		// 当用户输入 1 或 im 时，即进入导入文件界面
		if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("im")) {
			// 刷新
			refresh();
			// 调用导入文件方法
			importFile(scan, 2);
		}

		// 当用户输入 2 或 sh 时，即进入显示联系人界面
		if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("sh")) {
			// 刷新
			refresh();

			// 提示用户进行选择
			System.out.println("选项：");
			System.out.println("  -1.显示联系人(sc)");
			System.out.println("  -2.显示分组(sg)");
			System.out.println("  -0.返回(ba)");
			System.out.print("\n请用户选择要执行的操作(输入编号或括号里的字母皆可)：");

			// 读取用户输入
			String tempInput1 = scan.next();

			// 刷新
			refresh();

			// 如果用户选择显示联系人
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("sc")) {
				// 调用显示联系人方法
				showContents(scan);
			}

			// 如果用户选择显示分组
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("sg")) {
				// 调用添加分组方法
				showGroup(scan);
			}

		}

		// 当用户输入 3 或 ad 时，即进入添加信息界面
		if (userInput.equalsIgnoreCase("3") || userInput.equalsIgnoreCase("ad")) {
			// 刷新
			refresh();

			// 提示用户进行选择
			System.out.println("选项：");
			System.out.println("  -1.添加联系人(ac)");
			System.out.println("  -2.添加分组(ag)");
			System.out.println("  -0.返回(ba)");
			System.out.print("\n请用户选择要执行的操作(输入编号或括号里的字母皆可)：");

			// 读取用户输入
			String tempInput1 = scan.next();

			// 如果用户选择添加联系人
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("ac")) {
				// 调用添加联系人方法
				addContent(scan);
			}

			// 如果用户选择添加分组
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("ag")) {
				// 调用添加分组方法
				addGroup(scan);
			}
		}

		// 当用户输入 4 或 de 时，即进入删除联系人界面
		if (userInput.equalsIgnoreCase("4") || userInput.equalsIgnoreCase("de")) {
			// 刷新
			refresh();

			// 提示用户进行选择
			System.out.println("选项：");
			System.out.println("  -1.删除联系人(dc)");
			System.out.println("  -2.删除分组(dg)");
			System.out.println("  -0.返回(ba)");
			System.out.print("\n请用户选择要执行的操作(输入编号或括号里的字母皆可)：");

			// 读取用户输入
			String tempInput1 = scan.next();

			// 刷新
			refresh();

			// 如果用户选择删除联系人
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("dc")) {
				// 调用删除联系人方法
				delContent(scan);
			}

			// 如果用户选择删除分组
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("dg")) {
				// 调用删除分组方法
				delGroup(scan);
			}

		}

		return "0";
	}

	/**
	 * 删除分组
	 * 
	 * @param scan
	 */
	private void delGroup(Scanner scan) {

		// 先判断分组是否存在
		if (groups.isEmpty()) {
			System.out.println("没有分组，无需删除");
			// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
			System.out.print("按'0'键返回：");
			String userInput = scan.next();
			return;
		}

		// 遍历分组 HashMap
		Iterator<Entry<String, String>> iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "： " + entry.getValue());
		}

		System.out.print("\n请选择需要删除的分組的编号(请注意，该分组删除后，分组内的联系人一并删除)：");
		String userInput = scan.next();
		// 判断用户是否输入正确
		while (groups.get(userInput) == null) {
			System.out
					.println("\n输入有误，请重新输入要删除的联系人编号(请注意，该分组删除后，分组内的联系人一并删除)：");
			userInput = scan.next();
		}

		// 从 peoples 中将该编号的联系人删除
		groups.remove(userInput);

		/***
		 * 
		 * 
		 * 
		 * 刪除所有此分組的联系人
		 * 
		 * 
		 */

		// 保存
		this.run();

		// 遍历分组 HashMap
		iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "： " + entry.getValue());
		}

		System.out.print("\n是否继续删除分組[Y/N]：");
		userInput = scan.next();
		if (userInput.equalsIgnoreCase("y")) {
			delGroup(scan);
		}

	}

	/**
	 * 删除联系人方法
	 * 
	 * @param scan
	 */
	private void delContent(Scanner scan) {

		// 先判断是否有联系人存在
		if (peoples.isEmpty()) {
			System.out.println("没有联系人，无需删除");
			// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
			System.out.print("按'0'键返回：");
			String userInput = scan.next();
			return;
		}

		// 遍历整个peoples HashMap，将联系人信息打印出来
		Iterator<Entry<String, People>> iter = peoples.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, People> entry = iter.next();
			entry.getValue().showR(); // 调用People showR()方法
		}

		System.out.print("\n请选择需要删除的联系人的编号：");
		String userInput = scan.next();
		// 判断用户是否输入正确
		while (peoples.get(userInput) == null) {
			System.out.println("\n输入有误，请重新输入要删除的联系人编号：");
			userInput = scan.next();
		}

		// 从 peoples 中将该编号的联系人删除
		peoples.remove(userInput);

		// 保存
		this.run();

		//
		refresh();

		// 遍历整个peoples HashMap，将联系人信息打印出来
		iter = peoples.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, People> entry = iter.next();
			entry.getValue().showR(); // 调用People showR()方法
		}

		System.out.print("\n是否继续删除联系人[Y/N]：");
		userInput = scan.next();
		if (userInput.equalsIgnoreCase("y")) {
			delContent(scan);
		}
	}

	/**
	 * 添加分组方法
	 * 
	 * @param scan
	 */
	private void addGroup(Scanner scan) {

		System.out.println("请按以下格式输入分组信息");
		System.out.println("  分组号 分组名");
		System.out.print("请输入：");

		// 读取用户输入
		String groupInfo = scan.nextLine();
		// 防止 userInfo 读入空字符
		while (groupInfo.equalsIgnoreCase("")) {
			groupInfo = scan.nextLine();
		}

		// 用空格来拆分用户输入的信息
		String[] groupInfoArr = groupInfo.split(" ");
		// 将其添加到 gourps HashMap 中
		groups.put(groupInfoArr[0], groupInfoArr[1]);
		// 显示新添加的组的信息
		System.out
				.println(groupInfoArr[0] + ": " + groups.get(groupInfoArr[0]));
		// 提示分组信息导入成功
		System.out.println("---------------");
		System.out.println("分组信息添加成功！");
		System.out.println("---------------\n");

		// 保存文件
		this.run();

		// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
		System.out.print("按'0'键返回：");
		groupInfo = scan.next();
	}

	/**
	 * 添加联系人方法
	 * 
	 * @param scan
	 */
	private void addContent(Scanner scan) {

		// 先判断用户是否导入分组信息，如果没导入则需要先导入分组信息
		if (groups.isEmpty()) {
			System.out.print("没有找到分组信息，请用户先导入分组信息[Y/N]：");

			// 读取用户输入
			String userInput = scan.next();

			// 如果用户选择Y 则调用导入联系人方法
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// 调用导入联系人方法
				importGroupFile(scan, 2);
			} else { // 如果用户没有选择Y，则直接返回主界面
				return;
			}
			// 将 People 的编号初始化为0
			People.setNUM_OF_PEOPLE(0);
		}

		System.out.println("请按以下格式输入联系人信息");
		System.out.println("  姓名 性别 生日 电话1 电话2 电话3 所在地 分组(没有的信息填“无”)");
		System.out.print("请输入：");
		// 读取用户输入
		String userInfo = scan.nextLine();
		// 防止 userInfo 读入空字符
		while (userInfo.equalsIgnoreCase("")) {
			userInfo = scan.nextLine();
		}

		// 用空格来拆分用户输入的信息
		String[] userInfoArr = userInfo.split(" ");

		// 创建新的 People 实例
		People newPeople = new People(userInfoArr[0], userInfoArr[1],
				userInfoArr[2], userInfoArr[3], userInfoArr[4], userInfoArr[5],
				userInfoArr[6], userInfoArr[7]);
		// 更新 People 的分组名
		newPeople.updateGroupName(groups.get(userInfoArr[7]));
		// 将新的 People 实例添加到 HashMap 中
		peoples.put(newPeople.getID(), newPeople);

		// 刷新
		refresh();
		// 显示先添加的联系人的信息
		newPeople.showD();
		// 提示联系人信息导入成功
		System.out.println("---------------");
		System.out.println("联系人信息添加成功！");
		System.out.println("---------------\n");

		// 保存文件
		this.run();

		// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
		System.out.print("按'0'键返回：");
		userInfo = scan.next();
	}

	/**
	 * 显示分组
	 * 
	 * @param scan
	 */
	private void showGroup(Scanner scan) {
		// 先判断分组是否有内容
		if (groups.isEmpty()) {
			System.out.print("没有分组，请用户先导入或添加分组[Y/N]：");

			// 读取用户输入
			String userInput = scan.next();

			// 如果用户选择Y 则调用导入分组方法
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// 调用导入分组方法
				importGroupFile(scan, 2);
			} else { // 如果用户没有选择Y，则直接返回主界面
				return;
			}
		}

		// 遍历分组 HashMap
		Iterator<Entry<String, String>> iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "： " + entry.getValue());
		}

		// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
		System.out.print("\n按'0'键返回：");
		String userInput = scan.next();

	}

	/**
	 * 显示联系人方法
	 * 
	 * @param scan
	 */
	private void showContents(Scanner scan) {

		// 先判断联系人是否有内容
		if (peoples.isEmpty()) {
			System.out.print("没有联系人，请用户先导入或添加联系人[Y/N]：");

			// 读取用户输入
			String userInput = scan.next();

			// 如果用户选择Y 则调用导入联系人方法
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// 调用导入联系人方法
				importFile(scan, 2);
			} else { // 如果用户没有选择Y，则直接返回主界面
				return;
			}
		}

		// 提示用户有两种显示方式
		System.out.println("选项：");
		System.out.println("  -1.显示详细信息(sd)");
		System.out.println("  -2.显示粗略信息(sr)");
		System.out.println("  -0.返回(ba)");
		System.out.print("\n请用户选择要执行的操作(输入编号或括号里的字母皆可)：");

		// 读取用户输入
		String userInput = scan.next();

		// 如果选择显示详细信息
		if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("sd")) {
			// 刷新
			refresh();

			// 遍历整个peoples HashMap
			Iterator<Entry<String, People>> iter = peoples.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, People> entry = iter.next();
				entry.getValue().showD(); // 调用People showD()方法
			}

			// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
			System.out.print("按'0'键返回：");
			userInput = scan.next();
		}

		// 如果选择显示粗略信息
		if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("sr")) {
			// 刷新
			refresh();

			// 遍历整个peoples HashMap
			Iterator<Entry<String, People>> iter = peoples.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, People> entry = iter.next();
				entry.getValue().showR(); // 调用People showR()方法
			}

			// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
			System.out.print("按'0'键返回：");
			userInput = scan.next();
		}

		// 如果选择返回，则什么都不需要做
		if (userInput.equalsIgnoreCase("0") || userInput.equalsIgnoreCase("ba")) {

		}

	}

	/**
	 * 从电脑中导入文件
	 * 
	 * @param scan
	 */
	private void importFile(Scanner scan, int times) {
		// 导入分组文件
		importGroupFile(scan, times);

		// 导入联系人文件
		importContFile(scan, times);
	}

	/**
	 * 从电脑中导入联系人文件
	 * 
	 * @param scan
	 */
	private void importContFile(Scanner scan, int times) {

		// 当系统内置文件 contacts.txt 不存在时 提示用户自行导入数据
		if (!Tools.fileHasContent(Tools.CONT_FILE_NAME) || times != 1) {
			// 提示用户导入分组信息
			System.out.println("提示：在Windows下输入文件名路径时请使用\"/\"。");
			System.out.print("请输入要导入的联系人文件名(没有请输入 n )： ");
			CONT_FILE_NAME = scan.nextLine();

			// 防止 CONT_FILE_NAME 读入空值
			while (CONT_FILE_NAME.equals("")) {
				// 读取用户输入的文件路径
				CONT_FILE_NAME = scan.nextLine();
			}

			z:
			// 如果输入的不是 n 或 N
			if (!CONT_FILE_NAME.equalsIgnoreCase("n")) {
				// 如果用户输入的文件不存在或没有内容，则提示用户重新输入
				while (!Tools.fileHasContent(CONT_FILE_NAME)) {
					System.out.println("提示：在Windows下输入文件名路径时请使用\"/\"。");
					System.out.print("错误：输入有误，请重新输入(没有请输入 n )： ");
					CONT_FILE_NAME = scan.nextLine();
					// 如果用户此时输入 n 则退出
					if (CONT_FILE_NAME.equalsIgnoreCase("n")) {
						break z; // 直接跳出 if 语句
					}
				}

				// 得到文本里的内容
				ArrayList<String> peopleTempList = new ArrayList<String>();
				peopleTempList = Tools.readFileByLines(CONT_FILE_NAME);

				System.out.println();

				// 将其写入HashMap中
				for (String str : peopleTempList) {

					String[] temp = str.split(" ");
					// 实例化一个People
					People tempPeople = new People(temp[1], temp[2], temp[3],
							temp[4], temp[5], temp[6], temp[7], temp[8]);
					// 根据输入的分组信息，更新People的分组名
					tempPeople.updateGroupName(groups.get(temp[8]));
					peoples.put(tempPeople.getID(), tempPeople);
					tempPeople.showD();
				}

				// 保存
				this.run();

				// 提示联系人信息导入成功
				System.out.println("---------------");
				System.out.println("联系人信息导入成功！");
				System.out.println("---------------\n");

				// 等待用户按键返回（为了显示联系人，不然用户还没看到联系人就直接返回了）
				System.out.print("按'0'键返回：");
				String userInput = scan.next();

			}
		} else {
			CONT_FILE_NAME = Tools.CONT_FILE_NAME;

			// 得到文本里的内容
			ArrayList<String> peopleTempList = new ArrayList<String>();
			peopleTempList = Tools.readFileByLines(CONT_FILE_NAME);

			System.out.println();

			// 将其写入HashMap中
			for (String str : peopleTempList) {

				String[] temp = str.split(" ");
				// 实例化一个People
				People tempPeople = new People(temp[1], temp[2], temp[3],
						temp[4], temp[5], temp[6], temp[7], temp[8]);
				// 根据输入的分组信息，更新People的分组名
				tempPeople.updateGroupName(groups.get(temp[8]));
				peoples.put(tempPeople.getID(), tempPeople);
			}
		}
	}

	/**
	 * 从电脑中导入分组文件
	 * 
	 * @param scan
	 */
	private void importGroupFile(Scanner scan, int times) {

		if (!Tools.fileHasContent(Tools.GROUP_FILE_NAME) || times != 1) {
			// 提示用户导入分组信息
			System.out.println("提示：在Windows下输入文件名路径时请使用\"/\"。");
			System.out.print("请输入要导入的分组文件名(没有请输入 n )： ");

			// 读取用户输入的文件路径
			GROUP_FILE_NAME = scan.nextLine();

			// 防止 GROUP_FILE_NAME 读入空值
			while (GROUP_FILE_NAME.equals("")) {
				// 读取用户输入的文件路径
				GROUP_FILE_NAME = scan.nextLine();
			}

			z:
			// 如果输入的不是 n 或 N
			if (!GROUP_FILE_NAME.equalsIgnoreCase("n")) {
				// 如果用户输入的文件不存在或没有内容，则提示用户重新输入
				while (!Tools.fileHasContent(GROUP_FILE_NAME)) {
					System.out.println("提示：在Windows下输入文件名路径时请使用\"/\"。");
					System.out.print("错误：输入有误，请重新输入(没有请输入 n )： ");
					GROUP_FILE_NAME = scan.nextLine();
					// 如果用户此时输入 n 则退出
					if (GROUP_FILE_NAME.equalsIgnoreCase("n")) {
						break z; // 直接跳出 if 语句
					}
				}

				System.out.println();

				// 得到文本里的内容
				ArrayList<String> groupTempList = new ArrayList<String>();
				groupTempList = Tools.readFileByLines(GROUP_FILE_NAME);

				// 将其写入HashMap中
				for (String str : groupTempList) {
					String[] temp = str.split(" ");
					groups.put(temp[0], temp[1]);
				}

				// 提示分组信息导入成功
				System.out.println("---------------");
				System.out.println("分组信息导入成功！");
				System.out.println("---------------\n");

				// 保存
				this.run();
			}
		} else {
			GROUP_FILE_NAME = Tools.GROUP_FILE_NAME;

			// 得到文本里的内容
			ArrayList<String> groupTempList = new ArrayList<String>();
			groupTempList = Tools.readFileByLines(GROUP_FILE_NAME);

			// 将其写入HashMap中
			for (String str : groupTempList) {
				System.out.println(str);
				String[] temp = str.split(" ");
				groups.put(temp[0], temp[1]);
			}
		}

	}

	/**
	 * 保存内容
	 */
	private void saveFile() {

		// 将内容写入文件里
		Tools.writeFileByKind(Tools.GROUP_KIND);
		Tools.writeFileByKind(Tools.CONT_KIND);

		// 清空HashMap
		groups.clear();
		peoples.clear();

		People.setNUM_OF_PEOPLE(0);

		// 再从文件读出来达到保存的效果

		// 得到文本里的内容
		ArrayList<String> groupTempList = new ArrayList<String>();
		groupTempList = Tools.readFileByLines(Tools.GROUP_FILE_NAME);
		// 将其写入HashMap中
		for (String str : groupTempList) {
			String[] temp = str.split(" ");
			groups.put(temp[0], temp[1]);
		}
		// 得到文本里的内容
		ArrayList<String> peopleTempList = new ArrayList<String>();
		peopleTempList = Tools.readFileByLines(Tools.CONT_FILE_NAME);
		// 将其写入HashMap中
		for (String str : peopleTempList) {
			String[] temp = str.split(" ");
			// 实例化一个People
			People tempPeople = new People(temp[1], temp[2], temp[3], temp[4],
					temp[5], temp[6], temp[7], temp[8]);
			// 根据输入的分组信息，更新People的分组名
			tempPeople.updateGroupName(groups.get(temp[8]));
			peoples.put(tempPeople.getID(), tempPeople);
		}

	}

	// 在控制台输出空格，达到刷新的效果
	public void refresh() {
		refresh(30);
	}

	public void refresh(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println();
		}
	}

	// main函数
	public static void main(String[] args) {
		Client client = new Client();
		client.work();
	}

	// 多线程
	@Override
	public void run() {
		// 保存文件放到其他线程中
		saveFile();
	}
}
