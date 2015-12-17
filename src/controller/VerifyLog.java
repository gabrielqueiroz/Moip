package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VerifyLog {
	private List<String> requestTo = new ArrayList<>();
	private List<String> responseStatus = new ArrayList<>();
	
	/**
	 * Analisa cada linha do arquivo "log.txt", realizando as seguintes tarefas:
	 * Parse da String baseado em espaço movendo para um array de String webhook.
	 * Analise do array webhook em busca dos componentes "request_to" e "response_status".
	 * Parse deste componente baseado em aspas "", armazenando seu valor em um array local correspondente.
	 */
	public void loadLists(){
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
	
	/**
	 * Utiliza array local baseado apenas em valores unicos presentes nas listas locais de requestTo e responseStatus 
	 * Collections.frequency utilizado para verificar a frequencia de uma String dentro da lista.
	 * Utiliza uma lista auxiliar para ser ordenado com base em sua frequencia.
	 * Exibe os Top 3 requestTo.
	 * Exibe em ordem decrescente os status.
	 */
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
