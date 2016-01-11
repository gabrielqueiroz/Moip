package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel pela leitura de arquivos do sistema.
 * 
 * @author gqueiroz
 *
 */
public class ReadFile {	
	
	/**
	 * Realiza a leitura de um arquivo de texto (.txt), 
	 * retornando uma lista de Strings contendo cada linha do arquivo.
	 * 
	 * @param file Arquivo de texto para ser lido.
	 * @return Lista de String contendo cada linha do arquivo
	 */
	public List<String> lineToString(String file) throws IOException{
		List<String> temp = new ArrayList<>();		
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;		    
		    while ((line = br.readLine()) != null) 
		    	temp.add(line);		    
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado: "+e);
			e.printStackTrace();
			throw e;			
		} catch (IOException e) {
			System.out.println("IOException: "+e);
			e.printStackTrace();
			throw e;
		}
		
		return temp;
	}
}
