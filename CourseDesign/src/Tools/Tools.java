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

	// ���ݲ�ͬ�� kind��������д�벻ͬ���ļ���
	public static int CONT_KIND = 1001;
	public static int GROUP_KIND = 1002;

	// д�ļ����ļ�Ĭ����
	public static String CONT_FILE_NAME = "contacts.txt";
	public static String GROUP_FILE_NAME = "groups.txt";

	/**
	 * �ж�һ���ļ��Ƿ������������
	 * 
	 * @param filename
	 * @return boolean
	 */
	public static boolean fileHasContent(String filename) {
		File file = new File(filename);
		// ����ļ�������
		if (!file.exists()) {
			return false;
		}
		// ����ļ�û����
		if (file.length() == 0)
			return false;
		return true;
	}

	/**
	 * ������ת��Ϊȫƴ
	 *
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		// ���ú���ƴ������ĸ�ʽ

		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// �ж��ܷ�Ϊ�����ַ�

				// System.out.println(t1[i]);

				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// �����ֵļ���ȫƴ���浽t2������

					t4 += t2[0];// ȡ���ú���ȫƴ�ĵ�һ�ֶ��������ӵ��ַ���t4��

				} else {
					// ������Ǻ����ַ������ȡ���ַ������ӵ��ַ���t4��

					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * һ��һ�еض�ȡ�ļ�����
	 * 
	 * @param fileName
	 * @return ����һ�����ļ�ÿһ����ɵ�ArrayList
	 */
	public static ArrayList<String> readFileByLines(String fileName) {
		ArrayList<String> stringList = new ArrayList<String>();

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// �����ݱ�����ArrayList��
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
	 * д�ļ�
	 * 
	 * @param fileName
	 * @param kind
	 */
	public static void writeFileByKind(int kind) {
		if (kind == CONT_KIND) {
			// Ĭ������Ϊ contacts.txt
			String fileName = CONT_FILE_NAME;
			File file = new File(fileName);
			try {
				file.createNewFile();
				FileOutputStream txtFile = new FileOutputStream(file);
				PrintStream p = new PrintStream(txtFile);

				// ����peoples
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

				// ����groups
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
