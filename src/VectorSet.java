import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class VectorSet {

	private int[] chars = new int[26];
	private String lang;
	private String filename;
	
	public VectorSet(String filename, String lang){
		this.lang = lang;
		this.filename = filename;
		initVectorSet();
	}
	
	public VectorSet(String filename){
		this.filename = filename;
		this.lang = "";
		initVectorSet();
	}
	
	private void initVectorSet(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String strLine;
			char curr;
			
			while ((strLine = br.readLine()) != null){
				
				strLine = strLine.toLowerCase();
				strLine = strLine.trim();
				
				for (int i = 0; i < strLine.length(); i++){
					curr = strLine.charAt(i);
					
					if (Character.isLetter(curr))
						chars[((int) curr) - 97]++; //ASCII conversion
					else
						continue;
				}
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Filename: '" + this.filename + "', language: " + this.lang + "\n");
		sb.append("(");
		for (int i : chars)
			sb.append(i + ", ");
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		
		return sb.toString();
	}
	
	public int getValueAt(int index){
		return chars[index];
	}
	
	public String getLang(){
		return this.lang;
	}
	
	public int[] getArr(){
		return Arrays.copyOf(chars, chars.length);
	}
	
}
