import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class Main {
	
	private static synchronized void parseFile(String filePath) {
		File file = null;
		BufferedReader reader = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			reader = new BufferedReader(new FileReader(file));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			String[] content = builder.toString().split(" ");
			List<String> latonized = new ArrayList<String>();
			for (int i = 0; i < content.length; i++) {
				latonized.add(pigLatonize(content[i]));
			}
			for (String latonizedString : latonized) {
				System.out.println(latonizedString);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static synchronized String pigLatonize(String string) {
		char firstChar = string.charAt(0);
		String str = string.substring(1, string.length());
		str = str.concat("-" + firstChar + "ay");
		return str;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			new Runnable() {
				@Override
				public void run() {
					// File must contain your english to be translated into Pig Latin
					parseFile("english_file.txt");
				}
			}
		);
	}
	
}
