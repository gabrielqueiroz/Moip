package controller;

import java.util.List;

import model.Webhook;

/**
 * 
 * Classe responsavel por realizar a simulacao da chamada dos metodos
 * 
 * @author gqueiroz
 *
 */
public class Moip {
	
	public static void main(String[] args) {	

		ReadFile readFile = new ReadFile();		
		List<String> log = readFile.lineToString("src/log.txt");
		
		VerifyLog verifyLog = new VerifyLog();
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		
		System.out.println(verifyLog.getStatistics(listWebhooks, "requestTo", 3));
		
		System.out.println(verifyLog.getStatistics(listWebhooks, "status", 0));
		
		System.out.println(verifyLog.getStatistics(listWebhooks, "responseHeaders", 3));
		
		System.out.println(verifyLog.getStatistics(listWebhooks, "level", 3)); 
		
		System.out.println(verifyLog.getStatistics(listWebhooks, "responseBody", 3));  
		
	}
}
