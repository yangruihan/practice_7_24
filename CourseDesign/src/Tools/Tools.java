package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import Client.Client;
import Client.People;

public class Tools {

	// 根据不同的 kind，将数据写入不同的文件中
	public static int CONT_KIND = 1001;
	public static int GROUP_KIND = 1002;

	// 写文件的文件默认名
	public static String CONT_FILE_NAME = "contacts.txt";
	public static String GROUP_FILE_NAME = "groups.txt";

	/**
	 * 判断一个文件是否存在且有内容
	 * 
	 * @param filename
	 * @return boolean
	 */
	public static boolean fileHasContent(String filename) {
		File file = new File(filename);
		// 如果文件不存在
		if (!file.exists()) {
			return false;
		}
		// 如果文件没内容
		if (file.length() == 0)
			return false;
		return true;
	}

	/**
	 * 将汉字转换为全拼
	 *
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		// 设置汉字拼音输出的格式

		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断能否为汉字字符

				// System.out.println(t1[i]);

				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中

					t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后

				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后

					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * 一行一行地读取文件内容
	 * 
	 * @param fileName
	 * @return 返回一个由文件每一行组成的ArrayList
	 */
	public static ArrayList<String> readFileByLines(String fileName) {
		ArrayList<String> stringList = new ArrayList<String>();

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// 将内容保存至ArrayList中
				stringList.add(tempString);
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

		return stringList;
	}

	/**
	 * 写文件
	 * 
	 * @param fileName
	 * @param kind
	 */
	public static void writeFileByKind(int kind) {
		if (kind == CONT_KIND) {
			// 默认命名为 contacts.txt
			String fileName = CONT_FILE_NAME;
			File file = new File(fileName);
			try {
				file.createNewFile();
				FileOutputStream txtFile = new FileOutputStream(file);
				PrintStream p = new PrintStream(txtFile);

				// 遍历peoples
				Iterator<Entry<String, People>> iter = Client.peoples
						.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, People> entry = iter.next();
					p.println(entry.getValue().getID() + " "
							+ entry.getValue().getName() + " "
							+ entry.getValue().getGender() + " "
							+ entry.getValue().getBirth() + " "
							+ entry.getValue().getPhone1() + " "
							+ entry.getValue().getPhone2() + " "
							+ entry.getValue().getPhone3() + " "
							+ entry.getValue().getLoc() + " "
							+ entry.getValue().getGroup());
				}

				txtFile.close();
				p.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (kind == GROUP_KIND) {
			String fileName = GROUP_FILE_NAME;
			File file = new File(fileName);
			try {
				file.createNewFile();
				FileOutputStream txtFile = new FileOutputStream(file);
				PrintStream p = new PrintStream(txtFile);

				// 遍历groups
				Iterator<Entry<String, String>> iter = Client.groups.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Entry<String, String> entry = iter.next();
					p.println(entry.getKey() + " " + entry.getValue());
				}

				txtFile.close();
				p.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
