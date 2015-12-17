package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
	public List<String> lineToString(){
		List<String> temp = new ArrayList<>();
		String file = "src/log.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    
		    while ((line = br.readLine()) != null) 
		    	temp.add(line);
		    
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: "+e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: "+e);
			e.printStackTrace();
		}
		
		return temp;
	}
}
