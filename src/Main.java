import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Main {

	String[] words;



	public Main() {
		try {
			words = readLines("corncob_lowercase.txt"); //Change this to whatever dictionary you want. Must have line break for each word, and everything must be in lowercase
		} catch (IOException e) {
			e.printStackTrace();
		}

		process();

	}

	public static void main(String[] args) {
		new Main();

	}

	public String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
	}

	public void process(){
		try {
			PrintWriter writer = new PrintWriter("XwithoutY.txt", "UTF-8");

			for(int i = 0; i < words.length; i++){
				if(words[i].length() > 4){
					for(int j = 0; j < words.length; j++){
						if((words[j].length() > 4) && (words[i].length() > words[j].length())){
							if(words[i].contains(words[j])){
								if(!(words[i].endsWith("ly")) && !(words[i].endsWith("ness")) && 
										!(words[i].endsWith("s")) && !(words[i].endsWith("al")) && 
										!(words[i].endsWith("ist")) && !(words[i].endsWith("ty")) && 
										!(words[i].endsWith("ment")) && !(words[i].endsWith("ate")) && 
										!(words[i].endsWith("able")) && !(words[i].endsWith("ible")) && 
										!(words[i].endsWith("ing")) && !(words[i].endsWith("ful")) && 
										!(words[i].endsWith("ed")) && !(words[i].startsWith("a")) && 
										!(words[i].endsWith("d"))){		
									boolean disp = true;

									for(int k = 0; k < words.length; k++){
										if((words[i].equals(words[k]+words[j])) || (words[i].equals(words[j]+words[k]))){
											disp = false;
										}
									}
									
									if(disp){
										String line = "You can't have " + words[i] + " without " + words[j];
										System.out.println(line);
										writer.println(line);
									}
								}
							}
						}
					}
				}
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done!");

	}

}
