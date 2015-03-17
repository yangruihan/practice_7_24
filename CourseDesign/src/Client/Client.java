package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import Tools.Tools;

/**
 * �ͻ����࣬������������
 * 
 * @author ����
 * @version 1.0
 */
public class Client implements Runnable {

	private static String GROUP_FILE_NAME; // �����ļ���
	private static String CONT_FILE_NAME; // ��ϵ���ļ���

	// �� HashMapͨ��<ID,People>�ķ�ʽ��ID�ź�People��һһ��Ӧ����
	public static HashMap<String, People> peoples = new HashMap<String, People>();
	// �� HashMapͨ��<Num,String>�ķ�ʽ��Group�ź�Group����һһ��Ӧ����
	public static HashMap<String, String> groups = new HashMap<String, String>();

	// ������
	public void work() {

		// ʵ����һ��Scanner �������ӿ���̨������
		Scanner scan = new Scanner(System.in);

		// ��ʼ��
		init();

		// ˢ��
		refresh();

		System.out.println("----------��ӭʹ�ø���ͨѶ¼----------");
		System.out.println("\t\t\t\t--by ����\n");

		// �ӵ����е����ļ�
		importFile(scan, 1);

		// ������ѭ��
		mainLoop(scan);

	}

	/**
	 * �����ʼ��
	 */
	private void init() {
		// People ����������
		People.setNUM_OF_PEOPLE(0);

		// ���groups�����ڣ��򴴽�һ��ʵ��
		if (groups == null) {
			groups = new HashMap<String, String>();
		}
		if (!groups.isEmpty()) {
			groups.clear();
		}

		// ���peoples�����ڣ��򴴽�һ��ʵ��
		if (peoples == null) {
			peoples = new HashMap<String, People>();
		}
		if (!peoples.isEmpty()) {
			peoples.clear();
		}
	}

	/**
	 * ������ѭ��
	 * 
	 * @param scan
	 * @param userInput
	 * @return
	 */
	private void mainLoop(Scanner scan) {

		// ˢ��
		refresh();

		// �����û�������ַ���
		String userInput = "";

		// ������ѭ�����˳����Ϊ��userInputΪ-1000ʱ
		while (!userInput.equalsIgnoreCase("-1000")) {

			// ����������
			System.out.println("-------------����ͨѶ¼-------------");
			System.out.println("\t\t\t\t--by ����\n");
			System.out.println("ѡ�");
			System.out.println("  -1.�����ļ�(im)");
			System.out.println("  -2.��ʾ��Ϣ(sh)");
			System.out.println("  -3.�����Ϣ(ad)");
			System.out.println("  -4.ɾ����Ϣ(de)");
			System.out.println("  -5.������ϵ��(fi)");
			System.out.println("  -9.����(ab)");
			System.out.println("  -0.�˳�(ex)");
			System.out.print("\n���û�ѡ��Ҫִ�еĲ���(�����Ż����������ĸ�Կ�)��");

			// �����û�����
			userInput = scan.next();

			// �ж��û����룬����������ִ�в�ͬ�Ĺ���
			userInput = judgeUserInput(scan, userInput);

			// ˢ��
			refresh();
		}
	}

	/**
	 * �����û������룬ִ�в�ͬ�Ĺ���
	 * 
	 * @param scan
	 * @param userInput
	 */
	private String judgeUserInput(Scanner scan, String userInput) {
		// ���û�ѡ���˳�ʱ��ѯ���û��Ƿ�ȷ��
		if (userInput.equalsIgnoreCase("0") || userInput.equalsIgnoreCase("ex")) {
			// ˢ��
			refresh();
			System.out.print("ȷ��Ҫ�˳���[Y/N]: ");
			// ��ȡ�û�����
			String tempInput = scan.next();
			// ����û�ȷ���˳�����userInput���Ϊ-1000
			// �����������
			if (tempInput.equalsIgnoreCase("y")) {
				
				// ����
				this.run();
				
				return "-1000";
			}
		}

		// ���û����� 1 �� im ʱ�������뵼���ļ�����
		if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("im")) {
			// ˢ��
			refresh();
			// ���õ����ļ�����
			importFile(scan, 2);
		}

		// ���û����� 2 �� sh ʱ����������ʾ��ϵ�˽���
		if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("sh")) {
			// ˢ��
			refresh();

			// ��ʾ�û�����ѡ��
			System.out.println("ѡ�");
			System.out.println("  -1.��ʾ��ϵ��(sc)");
			System.out.println("  -2.��ʾ����(sg)");
			System.out.println("  -0.����(ba)");
			System.out.print("\n���û�ѡ��Ҫִ�еĲ���(�����Ż����������ĸ�Կ�)��");

			// ��ȡ�û�����
			String tempInput1 = scan.next();

			// ˢ��
			refresh();

			// ����û�ѡ����ʾ��ϵ��
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("sc")) {
				// ������ʾ��ϵ�˷���
				showContents(scan);
			}

			// ����û�ѡ����ʾ����
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("sg")) {
				// ������ӷ��鷽��
				showGroup(scan);
			}

		}

		// ���û����� 3 �� ad ʱ�������������Ϣ����
		if (userInput.equalsIgnoreCase("3") || userInput.equalsIgnoreCase("ad")) {
			// ˢ��
			refresh();

			// ��ʾ�û�����ѡ��
			System.out.println("ѡ�");
			System.out.println("  -1.�����ϵ��(ac)");
			System.out.println("  -2.��ӷ���(ag)");
			System.out.println("  -0.����(ba)");
			System.out.print("\n���û�ѡ��Ҫִ�еĲ���(�����Ż����������ĸ�Կ�)��");

			// ��ȡ�û�����
			String tempInput1 = scan.next();

			// ����û�ѡ�������ϵ��
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("ac")) {
				// ���������ϵ�˷���
				addContent(scan);
			}

			// ����û�ѡ����ӷ���
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("ag")) {
				// ������ӷ��鷽��
				addGroup(scan);
			}
		}

		// ���û����� 4 �� de ʱ��������ɾ����ϵ�˽���
		if (userInput.equalsIgnoreCase("4") || userInput.equalsIgnoreCase("de")) {
			// ˢ��
			refresh();

			// ��ʾ�û�����ѡ��
			System.out.println("ѡ�");
			System.out.println("  -1.ɾ����ϵ��(dc)");
			System.out.println("  -2.ɾ������(dg)");
			System.out.println("  -0.����(ba)");
			System.out.print("\n���û�ѡ��Ҫִ�еĲ���(�����Ż����������ĸ�Կ�)��");

			// ��ȡ�û�����
			String tempInput1 = scan.next();

			// ˢ��
			refresh();

			// ����û�ѡ��ɾ����ϵ��
			if (tempInput1.equalsIgnoreCase("1")
					|| tempInput1.equalsIgnoreCase("dc")) {
				// ����ɾ����ϵ�˷���
				delContent(scan);
			}

			// ����û�ѡ��ɾ������
			if (tempInput1.equalsIgnoreCase("2")
					|| tempInput1.equalsIgnoreCase("dg")) {
				// ����ɾ�����鷽��
				delGroup(scan);
			}

		}

		return "0";
	}

	/**
	 * ɾ������
	 * 
	 * @param scan
	 */
	private void delGroup(Scanner scan) {

		// ���жϷ����Ƿ����
		if (groups.isEmpty()) {
			System.out.println("û�з��飬����ɾ��");
			// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
			System.out.print("��'0'�����أ�");
			String userInput = scan.next();
			return;
		}

		// �������� HashMap
		Iterator<Entry<String, String>> iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "�� " + entry.getValue());
		}

		System.out.print("\n��ѡ����Ҫɾ���ķֽM�ı��(��ע�⣬�÷���ɾ���󣬷����ڵ���ϵ��һ��ɾ��)��");
		String userInput = scan.next();
		// �ж��û��Ƿ�������ȷ
		while (groups.get(userInput) == null) {
			System.out
					.println("\n������������������Ҫɾ������ϵ�˱��(��ע�⣬�÷���ɾ���󣬷����ڵ���ϵ��һ��ɾ��)��");
			userInput = scan.next();
		}

		// �� peoples �н��ñ�ŵ���ϵ��ɾ��
		groups.remove(userInput);

		/***
		 * 
		 * 
		 * 
		 * �h�����д˷ֽM����ϵ��
		 * 
		 * 
		 */

		// ����
		this.run();

		// �������� HashMap
		iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "�� " + entry.getValue());
		}

		System.out.print("\n�Ƿ����ɾ���ֽM[Y/N]��");
		userInput = scan.next();
		if (userInput.equalsIgnoreCase("y")) {
			delGroup(scan);
		}

	}

	/**
	 * ɾ����ϵ�˷���
	 * 
	 * @param scan
	 */
	private void delContent(Scanner scan) {

		// ���ж��Ƿ�����ϵ�˴���
		if (peoples.isEmpty()) {
			System.out.println("û����ϵ�ˣ�����ɾ��");
			// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
			System.out.print("��'0'�����أ�");
			String userInput = scan.next();
			return;
		}

		// ��������peoples HashMap������ϵ����Ϣ��ӡ����
		Iterator<Entry<String, People>> iter = peoples.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, People> entry = iter.next();
			entry.getValue().showR(); // ����People showR()����
		}

		System.out.print("\n��ѡ����Ҫɾ������ϵ�˵ı�ţ�");
		String userInput = scan.next();
		// �ж��û��Ƿ�������ȷ
		while (peoples.get(userInput) == null) {
			System.out.println("\n������������������Ҫɾ������ϵ�˱�ţ�");
			userInput = scan.next();
		}

		// �� peoples �н��ñ�ŵ���ϵ��ɾ��
		peoples.remove(userInput);

		// ����
		this.run();

		//
		refresh();

		// ��������peoples HashMap������ϵ����Ϣ��ӡ����
		iter = peoples.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, People> entry = iter.next();
			entry.getValue().showR(); // ����People showR()����
		}

		System.out.print("\n�Ƿ����ɾ����ϵ��[Y/N]��");
		userInput = scan.next();
		if (userInput.equalsIgnoreCase("y")) {
			delContent(scan);
		}
	}

	/**
	 * ��ӷ��鷽��
	 * 
	 * @param scan
	 */
	private void addGroup(Scanner scan) {

		System.out.println("�밴���¸�ʽ���������Ϣ");
		System.out.println("  ����� ������");
		System.out.print("�����룺");

		// ��ȡ�û�����
		String groupInfo = scan.nextLine();
		// ��ֹ userInfo ������ַ�
		while (groupInfo.equalsIgnoreCase("")) {
			groupInfo = scan.nextLine();
		}

		// �ÿո�������û��������Ϣ
		String[] groupInfoArr = groupInfo.split(" ");
		// ������ӵ� gourps HashMap ��
		groups.put(groupInfoArr[0], groupInfoArr[1]);
		// ��ʾ����ӵ������Ϣ
		System.out
				.println(groupInfoArr[0] + ": " + groups.get(groupInfoArr[0]));
		// ��ʾ������Ϣ����ɹ�
		System.out.println("---------------");
		System.out.println("������Ϣ��ӳɹ���");
		System.out.println("---------------\n");

		// �����ļ�
		this.run();

		// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
		System.out.print("��'0'�����أ�");
		groupInfo = scan.next();
	}

	/**
	 * �����ϵ�˷���
	 * 
	 * @param scan
	 */
	private void addContent(Scanner scan) {

		// ���ж��û��Ƿ��������Ϣ�����û��������Ҫ�ȵ��������Ϣ
		if (groups.isEmpty()) {
			System.out.print("û���ҵ�������Ϣ�����û��ȵ��������Ϣ[Y/N]��");

			// ��ȡ�û�����
			String userInput = scan.next();

			// ����û�ѡ��Y ����õ�����ϵ�˷���
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// ���õ�����ϵ�˷���
				importGroupFile(scan, 2);
			} else { // ����û�û��ѡ��Y����ֱ�ӷ���������
				return;
			}
			// �� People �ı�ų�ʼ��Ϊ0
			People.setNUM_OF_PEOPLE(0);
		}

		System.out.println("�밴���¸�ʽ������ϵ����Ϣ");
		System.out.println("  ���� �Ա� ���� �绰1 �绰2 �绰3 ���ڵ� ����(û�е���Ϣ��ޡ�)");
		System.out.print("�����룺");
		// ��ȡ�û�����
		String userInfo = scan.nextLine();
		// ��ֹ userInfo ������ַ�
		while (userInfo.equalsIgnoreCase("")) {
			userInfo = scan.nextLine();
		}

		// �ÿո�������û��������Ϣ
		String[] userInfoArr = userInfo.split(" ");

		// �����µ� People ʵ��
		People newPeople = new People(userInfoArr[0], userInfoArr[1],
				userInfoArr[2], userInfoArr[3], userInfoArr[4], userInfoArr[5],
				userInfoArr[6], userInfoArr[7]);
		// ���� People �ķ�����
		newPeople.updateGroupName(groups.get(userInfoArr[7]));
		// ���µ� People ʵ����ӵ� HashMap ��
		peoples.put(newPeople.getID(), newPeople);

		// ˢ��
		refresh();
		// ��ʾ����ӵ���ϵ�˵���Ϣ
		newPeople.showD();
		// ��ʾ��ϵ����Ϣ����ɹ�
		System.out.println("---------------");
		System.out.println("��ϵ����Ϣ��ӳɹ���");
		System.out.println("---------------\n");

		// �����ļ�
		this.run();

		// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
		System.out.print("��'0'�����أ�");
		userInfo = scan.next();
	}

	/**
	 * ��ʾ����
	 * 
	 * @param scan
	 */
	private void showGroup(Scanner scan) {
		// ���жϷ����Ƿ�������
		if (groups.isEmpty()) {
			System.out.print("û�з��飬���û��ȵ������ӷ���[Y/N]��");

			// ��ȡ�û�����
			String userInput = scan.next();

			// ����û�ѡ��Y ����õ�����鷽��
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// ���õ�����鷽��
				importGroupFile(scan, 2);
			} else { // ����û�û��ѡ��Y����ֱ�ӷ���������
				return;
			}
		}

		// �������� HashMap
		Iterator<Entry<String, String>> iter = groups.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.out.println(entry.getKey() + "�� " + entry.getValue());
		}

		// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
		System.out.print("\n��'0'�����أ�");
		String userInput = scan.next();

	}

	/**
	 * ��ʾ��ϵ�˷���
	 * 
	 * @param scan
	 */
	private void showContents(Scanner scan) {

		// ���ж���ϵ���Ƿ�������
		if (peoples.isEmpty()) {
			System.out.print("û����ϵ�ˣ����û��ȵ���������ϵ��[Y/N]��");

			// ��ȡ�û�����
			String userInput = scan.next();

			// ����û�ѡ��Y ����õ�����ϵ�˷���
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println();
				// ���õ�����ϵ�˷���
				importFile(scan, 2);
			} else { // ����û�û��ѡ��Y����ֱ�ӷ���������
				return;
			}
		}

		// ��ʾ�û���������ʾ��ʽ
		System.out.println("ѡ�");
		System.out.println("  -1.��ʾ��ϸ��Ϣ(sd)");
		System.out.println("  -2.��ʾ������Ϣ(sr)");
		System.out.println("  -0.����(ba)");
		System.out.print("\n���û�ѡ��Ҫִ�еĲ���(�����Ż����������ĸ�Կ�)��");

		// ��ȡ�û�����
		String userInput = scan.next();

		// ���ѡ����ʾ��ϸ��Ϣ
		if (userInput.equalsIgnoreCase("1") || userInput.equalsIgnoreCase("sd")) {
			// ˢ��
			refresh();

			// ��������peoples HashMap
			Iterator<Entry<String, People>> iter = peoples.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, People> entry = iter.next();
				entry.getValue().showD(); // ����People showD()����
			}

			// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
			System.out.print("��'0'�����أ�");
			userInput = scan.next();
		}

		// ���ѡ����ʾ������Ϣ
		if (userInput.equalsIgnoreCase("2") || userInput.equalsIgnoreCase("sr")) {
			// ˢ��
			refresh();

			// ��������peoples HashMap
			Iterator<Entry<String, People>> iter = peoples.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Entry<String, People> entry = iter.next();
				entry.getValue().showR(); // ����People showR()����
			}

			// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
			System.out.print("��'0'�����أ�");
			userInput = scan.next();
		}

		// ���ѡ�񷵻أ���ʲô������Ҫ��
		if (userInput.equalsIgnoreCase("0") || userInput.equalsIgnoreCase("ba")) {

		}

	}

	/**
	 * �ӵ����е����ļ�
	 * 
	 * @param scan
	 */
	private void importFile(Scanner scan, int times) {
		// ��������ļ�
		importGroupFile(scan, times);

		// ������ϵ���ļ�
		importContFile(scan, times);
	}

	/**
	 * �ӵ����е�����ϵ���ļ�
	 * 
	 * @param scan
	 */
	private void importContFile(Scanner scan, int times) {

		// ��ϵͳ�����ļ� contacts.txt ������ʱ ��ʾ�û����е�������
		if (!Tools.fileHasContent(Tools.CONT_FILE_NAME) || times != 1) {
			// ��ʾ�û����������Ϣ
			System.out.println("��ʾ����Windows�������ļ���·��ʱ��ʹ��\"/\"��");
			System.out.print("������Ҫ�������ϵ���ļ���(û�������� n )�� ");
			CONT_FILE_NAME = scan.nextLine();

			// ��ֹ CONT_FILE_NAME �����ֵ
			while (CONT_FILE_NAME.equals("")) {
				// ��ȡ�û�������ļ�·��
				CONT_FILE_NAME = scan.nextLine();
			}

			z:
			// �������Ĳ��� n �� N
			if (!CONT_FILE_NAME.equalsIgnoreCase("n")) {
				// ����û�������ļ������ڻ�û�����ݣ�����ʾ�û���������
				while (!Tools.fileHasContent(CONT_FILE_NAME)) {
					System.out.println("��ʾ����Windows�������ļ���·��ʱ��ʹ��\"/\"��");
					System.out.print("����������������������(û�������� n )�� ");
					CONT_FILE_NAME = scan.nextLine();
					// ����û���ʱ���� n ���˳�
					if (CONT_FILE_NAME.equalsIgnoreCase("n")) {
						break z; // ֱ������ if ���
					}
				}

				// �õ��ı��������
				ArrayList<String> peopleTempList = new ArrayList<String>();
				peopleTempList = Tools.readFileByLines(CONT_FILE_NAME);

				System.out.println();

				// ����д��HashMap��
				for (String str : peopleTempList) {

					String[] temp = str.split(" ");
					// ʵ����һ��People
					People tempPeople = new People(temp[1], temp[2], temp[3],
							temp[4], temp[5], temp[6], temp[7], temp[8]);
					// ��������ķ�����Ϣ������People�ķ�����
					tempPeople.updateGroupName(groups.get(temp[8]));
					peoples.put(tempPeople.getID(), tempPeople);
					tempPeople.showD();
				}

				// ����
				this.run();

				// ��ʾ��ϵ����Ϣ����ɹ�
				System.out.println("---------------");
				System.out.println("��ϵ����Ϣ����ɹ���");
				System.out.println("---------------\n");

				// �ȴ��û��������أ�Ϊ����ʾ��ϵ�ˣ���Ȼ�û���û������ϵ�˾�ֱ�ӷ����ˣ�
				System.out.print("��'0'�����أ�");
				String userInput = scan.next();

			}
		} else {
			CONT_FILE_NAME = Tools.CONT_FILE_NAME;

			// �õ��ı��������
			ArrayList<String> peopleTempList = new ArrayList<String>();
			peopleTempList = Tools.readFileByLines(CONT_FILE_NAME);

			System.out.println();

			// ����д��HashMap��
			for (String str : peopleTempList) {

				String[] temp = str.split(" ");
				// ʵ����һ��People
				People tempPeople = new People(temp[1], temp[2], temp[3],
						temp[4], temp[5], temp[6], temp[7], temp[8]);
				// ��������ķ�����Ϣ������People�ķ�����
				tempPeople.updateGroupName(groups.get(temp[8]));
				peoples.put(tempPeople.getID(), tempPeople);
			}
		}
	}

	/**
	 * �ӵ����е�������ļ�
	 * 
	 * @param scan
	 */
	private void importGroupFile(Scanner scan, int times) {

		if (!Tools.fileHasContent(Tools.GROUP_FILE_NAME) || times != 1) {
			// ��ʾ�û����������Ϣ
			System.out.println("��ʾ����Windows�������ļ���·��ʱ��ʹ��\"/\"��");
			System.out.print("������Ҫ����ķ����ļ���(û�������� n )�� ");

			// ��ȡ�û�������ļ�·��
			GROUP_FILE_NAME = scan.nextLine();

			// ��ֹ GROUP_FILE_NAME �����ֵ
			while (GROUP_FILE_NAME.equals("")) {
				// ��ȡ�û�������ļ�·��
				GROUP_FILE_NAME = scan.nextLine();
			}

			z:
			// �������Ĳ��� n �� N
			if (!GROUP_FILE_NAME.equalsIgnoreCase("n")) {
				// ����û�������ļ������ڻ�û�����ݣ�����ʾ�û���������
				while (!Tools.fileHasContent(GROUP_FILE_NAME)) {
					System.out.println("��ʾ����Windows�������ļ���·��ʱ��ʹ��\"/\"��");
					System.out.print("����������������������(û�������� n )�� ");
					GROUP_FILE_NAME = scan.nextLine();
					// ����û���ʱ���� n ���˳�
					if (GROUP_FILE_NAME.equalsIgnoreCase("n")) {
						break z; // ֱ������ if ���
					}
				}

				System.out.println();

				// �õ��ı��������
				ArrayList<String> groupTempList = new ArrayList<String>();
				groupTempList = Tools.readFileByLines(GROUP_FILE_NAME);

				// ����д��HashMap��
				for (String str : groupTempList) {
					String[] temp = str.split(" ");
					groups.put(temp[0], temp[1]);
				}

				// ��ʾ������Ϣ����ɹ�
				System.out.println("---------------");
				System.out.println("������Ϣ����ɹ���");
				System.out.println("---------------\n");

				// ����
				this.run();
			}
		} else {
			GROUP_FILE_NAME = Tools.GROUP_FILE_NAME;

			// �õ��ı��������
			ArrayList<String> groupTempList = new ArrayList<String>();
			groupTempList = Tools.readFileByLines(GROUP_FILE_NAME);

			// ����д��HashMap��
			for (String str : groupTempList) {
				System.out.println(str);
				String[] temp = str.split(" ");
				groups.put(temp[0], temp[1]);
			}
		}

	}

	/**
	 * ��������
	 */
	private void saveFile() {

		// ������д���ļ���
		Tools.writeFileByKind(Tools.GROUP_KIND);
		Tools.writeFileByKind(Tools.CONT_KIND);

		// ���HashMap
		groups.clear();
		peoples.clear();

		People.setNUM_OF_PEOPLE(0);

		// �ٴ��ļ��������ﵽ�����Ч��

		// �õ��ı��������
		ArrayList<String> groupTempList = new ArrayList<String>();
		groupTempList = Tools.readFileByLines(Tools.GROUP_FILE_NAME);
		// ����д��HashMap��
		for (String str : groupTempList) {
			String[] temp = str.split(" ");
			groups.put(temp[0], temp[1]);
		}
		// �õ��ı��������
		ArrayList<String> peopleTempList = new ArrayList<String>();
		peopleTempList = Tools.readFileByLines(Tools.CONT_FILE_NAME);
		// ����д��HashMap��
		for (String str : peopleTempList) {
			String[] temp = str.split(" ");
			// ʵ����һ��People
			People tempPeople = new People(temp[1], temp[2], temp[3], temp[4],
					temp[5], temp[6], temp[7], temp[8]);
			// ��������ķ�����Ϣ������People�ķ�����
			tempPeople.updateGroupName(groups.get(temp[8]));
			peoples.put(tempPeople.getID(), tempPeople);
		}

	}

	// �ڿ���̨����ո񣬴ﵽˢ�µ�Ч��
	public void refresh() {
		refresh(30);
	}

	public void refresh(int n) {
		for (int i = 0; i < n; i++) {
			System.out.println();
		}
	}

	// main����
	public static void main(String[] args) {
		Client client = new Client();
		client.work();
	}

	// ���߳�
	@Override
	public void run() {
		// �����ļ��ŵ������߳���
		saveFile();
	}
}
