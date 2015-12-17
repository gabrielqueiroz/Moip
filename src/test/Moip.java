package test;

import controller.VerifyLog;

public class Moip {
	public static void main(String[] args) {
		VerifyLog verifyLog = new VerifyLog();
		verifyLog.loadLists();
		verifyLog.showValues();
	}
}
