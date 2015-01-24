package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFromFile {

	/**
	 * read file by chars
	 */
	public static void readFileByChars(String fileName) {

		File file = new File(fileName);
		Reader reader = null;

		try {
			System.out.println("reading file by chars......");

			try {
				reader = new InputStreamReader(new FileInputStream(file));

				int tempchar;
				while (-1 != (tempchar = reader.read())) {
					// For windows, \r\n means a newline
					// but if we use \r or \n alone, the content will have two
					// newlines
					// so we should delete one of them.
					if ('\r' != ((char) tempchar)) {
						System.out.print((char) tempchar);
					}
				}// end of while
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}// end of try...catch
				}// end of if (reader != null)
			}// end of finally
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * read file by lines
	 */
	public static void readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("reading file by lines......");

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				System.out.println("line" + line + ":" + tempString);
				line++;
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
}
