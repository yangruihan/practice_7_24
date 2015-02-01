package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import Friends.Client;
import Friends.Date;
import Friends.People;

public class ReadFromFile {

	// the file kind
	public static int KIND_GROUP = -99; // 分组信息
	public static int KIND_CONTACTS = -90; // 通讯录内容
	public static int KIND_USER_INFO = -85; // 个人信息

	/**
	 * read file by chars
	 */
	// public static void readFileByChars(String fileName, int kind) {
	//
	// File file = new File(fileName);
	// Reader reader = null;
	//
	// try {
	// System.out.println("reading file by chars......");
	//
	// try {
	// reader = new InputStreamReader(new FileInputStream(file));
	//
	// int tempchar;
	// while (-1 != (tempchar = reader.read())) {
	// // For windows, \r\n means a newline
	// // but if we use \r or \n alone, the content will have two
	// // newlines
	// // so we should delete one of them.
	// if ('\r' != ((char) tempchar)) {
	// System.out.print((char) tempchar);
	// }
	// }// end of while
	// } finally {
	// if (reader != null) {
	// try {
	// reader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }// end of try...catch
	// }// end of if (reader != null)
	// }// end of finally
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * read file by lines
	 */
	public static void readFileByLines(String fileName, int kind) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {

				// 传入的内容是分组信息
				if (kind == KIND_GROUP) {
					String[] ary = tempString.split(" ");
					Client.group.put(Integer.valueOf(ary[0]), ary[1]);
				}

				if (kind == KIND_CONTACTS) {
					String[] ary = tempString.split(" ");

					putPeople(ary);
				}

				if (kind == KIND_USER_INFO) {
					String[] ary = tempString.split(" ");
					Client.userName = ary[0];
					Client.userGender = ary[1];
					Client.userBirth = ary[2];
					Client.userPhoneNum = ary[3];
					Client.userPhoneNum2 = ary[4];
					Client.userQQNum = ary[5];
					Client.userLocation = ary[6];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private static void putPeople(String[] ary) {
		// 如果分组是订餐
		if (ary[0].equals("6")) {
			People res = null;
			// 至少有3个参数
			if (ary.length == 4) {
				res = new People(Integer.parseInt(ary[0]), ary[1], "/",
						new Date(0, 0, 0), ary[2], ary[3]);
			}
			if (ary.length == 5) {
				res = new People(Integer.parseInt(ary[0]), ary[1], "/",
						new Date(0, 0, 0), ary[2], ary[3], ary[4]);
			}
			if (ary.length == 6) {
				res = new People(Integer.parseInt(ary[0]), ary[1], "/",
						new Date(0, 0, 0), ary[2], ary[3], ary[4], ary[5]);
			}
			if (ary.length == 8) {
				res = new People(Integer.parseInt(ary[0]), ary[1], ary[2],
						new Date(0, 0, 0), ary[4], ary[5], ary[6], ary[7]);
			}
			Client.contacts.put(res.getIDNumber(), res);
		} // 如果分组不是订餐
		else {
			String[] date = ary[3].split("-");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			Date birth = new Date(year, month, day);
			People peo = null;

			if (ary.length == 8) {
				peo = new People(Integer.parseInt(ary[0]), ary[1], ary[2],
						birth, ary[4], ary[5], ary[6], ary[7]);
			}
			if (ary.length == 7) {
				peo = new People(Integer.parseInt(ary[0]), ary[1], ary[2],
						birth, ary[4], ary[5], ary[6]);
			}
			if (ary.length == 6) {
				peo = new People(Integer.parseInt(ary[0]), ary[1], ary[2],
						birth, ary[4], ary[5]);
			}
			Client.contacts.put(peo.getIDNumber(), peo);
		}
	}
}
