package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
	/**
	 * Realiza a leitura de um arquivo de texto "log.txt" na pasta "scr" do projeto, 
	 * retornando uma list de strings contendo cada lista do arquivo.
	 * 
	 * @return Lista de String contendo cada linha do arquivo
	 */
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
