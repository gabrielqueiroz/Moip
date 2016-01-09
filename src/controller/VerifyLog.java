package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Webhook;

/**
 * 
 * Classe responsavel pela analise de webhooks presentes em um log.
 * 
 * @author gqueiroz
 *
 */
public class VerifyLog {
		
	public List<Webhook> getWebhook(List<String> log){
		List<Webhook> listWebhooks = new ArrayList<>();		
		
		for(String line : log){
			Webhook webhook = new Webhook();			
			
			String[] aux = line.split("\\s+(?![^\\[]*\\])");
		
			for(String temp : aux){
				if(temp.contains("level"))
					webhook.setLevel(temp.substring(6, temp.length()));
				else if(temp.contains("response_body"))
					webhook.setResponseBody(temp.substring(15, temp.length()));
				else if(temp.contains("request_to"))
					webhook.setRequestTo(temp.substring(11, temp.length()));
				else if(temp.contains("status"))
					webhook.setResponseStatus(temp.substring(16, temp.length()));
				else if(temp.contains("response_headers"))
					webhook.setResponseHeaders(temp.substring(17, temp.length()));
				else 
					webhook.setResponseHeaders(webhook.getResponseHeaders()+" "+temp);
			}	
				
			listWebhooks.add(webhook);	
		}		
		
		return listWebhooks;
	} 
	
	private List<String> requestTo = new ArrayList<>();
	private List<String> responseStatus = new ArrayList<>();

	public void showValues() {
		Set<String> unique = new HashSet<String>(requestTo);
		List<String> aux = new ArrayList<String>();

		for (String key : unique)
			aux.add(Collections.frequency(requestTo, key) + " - " + key);

		Collections.sort(aux, Collections.reverseOrder());
		for (int i = 0; i < 3; i++)
			System.out.println(swapString(aux.get(i)));

		System.out.println("");

		aux.clear();
		unique = new HashSet<String>(responseStatus);
		for (String key : unique)
			aux.add(Collections.frequency(responseStatus, key) + " - " + key);

		Collections.sort(aux, Collections.reverseOrder());
		for (String status : aux)
			System.out.println(swapString(status));
	}

	/**
	 * Formata uma string "x-y" para "y-x".
	 * 
	 * @param String
	 *            a ser formatada.
	 * @return String formatada.
	 */
	public String swapString(String string) {
		String[] temp = string.split("-");
		string = temp[1] + " - " + temp[0];
		return string;
	}
}
