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
		List<String> log = readFile.lineToString("src/testMin.txt");
		
		VerifyLog verifyLog = new VerifyLog();
		List<Webhook> test = verifyLog.getWebhook(log);
	}
}
