package Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Friends.Client;

public class WriteToFile {

	public static int KIND_USER_INFO = 1001;

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

			txtFile.close();
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
