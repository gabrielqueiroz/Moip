package test;

import java.util.List;

import controller.ReadFile;
import controller.VerifyLog;

/**
 * 
 * Classe respons�vel por realizar o teste e exibi��o dos resultados.
 * 
 * @author gqueiroz
 *
 */
public class Moip {
	
	public static void main(String[] args) {		
		ReadFile readFile = new ReadFile();		
		List<String> log = readFile.lineToString("src/log.txt");
		
		VerifyLog verifyLog = new VerifyLog();
		verifyLog.loadLists(log);
		
		verifyLog.showValues();
	}
}
