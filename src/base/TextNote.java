package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class TextNote extends Note{
	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			File file = new File(absolutePath);
			BufferedReader br = new BufferedReader( new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) result += line;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		
		try {
			if (pathFolder == "") pathFolder = ".";
			File file = new File(pathFolder+ File.separator +getTitle().replace(" ", "_")+".txt");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(content);
			bw.close();
		} catch (Exception ex ) {
			ex.getStackTrace();
		}
	}
}
