package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VerifyLog {
	private List<String> requestTo = new ArrayList<>();
	private List<String> responseStatus = new ArrayList<>();
	
	public void loadArrays(){
		ReadFile readFile = new ReadFile();
		List<String> log = readFile.lineToString();
		for(String line : log){
			String[] webhook = line.split("\\s+");
			for(String temp : webhook){
				if(temp.contains("request_to"))				
					requestTo.add(temp.split("\"")[1]);
				if(temp.contains("response_status"))
					responseStatus.add(temp.split("\"")[1]);
			}
		}		
	}
	
	public void showValues(){
		Set<String> unique = new HashSet<String>(requestTo);
		List<String> aux = new ArrayList<String>();
		
		for (String key : unique) 
			aux.add(Collections.frequency(requestTo, key) + " - " +key);	
		
		Collections.sort(aux, Collections.reverseOrder());
		for(int i=0;i<3;i++)
			System.out.println(aux.get(i));
		
		aux.clear();
		unique = new HashSet<String>(responseStatus);
		for (String key : unique) 
			aux.add(Collections.frequency(responseStatus, key) + " - " +key);
		
		Collections.sort(aux, Collections.reverseOrder());
		for(String string : aux)
			System.out.println(string);
		
	}
}
