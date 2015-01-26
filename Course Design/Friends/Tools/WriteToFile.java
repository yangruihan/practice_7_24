package Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map.Entry;

import Friends.Client;
import Friends.People;

public class WriteToFile {

	public static int KIND_USER_INFO = 1001;
	public static int KIND_CON_INFO = 1101;
	public static int KIND_GROUP_INFO = 1201;

	public WriteToFile(String fileName) {

	}

	public static void writeFileByName(String fileName, int kind) {
		File file = new File(fileName);
		try {
			file.createNewFile();
			FileOutputStream txtFile = new FileOutputStream(file);
			PrintStream p = new PrintStream(txtFile);

			if (kind == KIND_USER_INFO) {
				p.println(Client.userName + " " + Client.userGender + " "
						+ Client.userBirth + " " + Client.userPhoneNum + " "
						+ Client.userPhoneNum2 + " " + Client.userQQNum + " "
						+ Client.userLocation);
			}
			if (kind == KIND_CON_INFO) {
				Iterator<Entry<Integer, People>> iter = Client.contacts
						.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<Integer, People> entry = iter.next();
					p.println(entry.getValue().getGroup() + " "
							+ entry.getValue().getName() + " "
							+ entry.getValue().getGender() + " "
							+ entry.getValue().getBirthday() + " "
							+ entry.getValue().getPhoneNum1() + " "
							+ entry.getValue().getPhoneNum2() + " "
							+ entry.getValue().getQQNum() + " "
							+ entry.getValue().getLocation());
				}
			}
			if (kind == KIND_GROUP_INFO) {
				Iterator<Entry<Integer, String>> iter = Client.group.entrySet().iterator();
				while (iter.hasNext()) {
 					Entry<Integer, String> entry = iter.next();
 					p.println(entry.getKey() + " " +entry.getValue());
				}
			}

			txtFile.close();
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
