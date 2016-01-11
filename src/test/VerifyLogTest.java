package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Webhook;

import org.junit.Before;
import org.junit.Test;

import controller.ReadFile;
import controller.VerifyLog;

public class VerifyLogTest {
	private VerifyLog verifyLog;	
	private ReadFile readFile;

	@Before
	public void initialize() {
		verifyLog = new VerifyLog();
		readFile = new ReadFile();
	}

	@Test
	public void getWebhookTest() {
		List<String> aux = new ArrayList<String>();
		aux.add("level=info "
				+ "response_body=\"\" "
				+ "request_to=\"https://grotesquemoon.de\" "
				+ "response_headers=map[Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X-Ua-Compatible:[IE=Edge,chrome=1]] "
				+ "response_status=\"201\"");
		List<Webhook> result = verifyLog.getWebhook(aux);
		Webhook expected = new Webhook(
				"info",
				"",
				"https://grotesquemoon.de",
				"map[Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X-Ua-Compatible:[IE=Edge,chrome=1]]",
				"201");
		assertEquals(expected.toString(), result.get(0).toString());
	}

	@Test
	public void isWebhookTestValid() {
		boolean result = verifyLog
				.isWebhook("level=info response_body=\"\" request_to=\"https://grotesquemoon.de\" response_headers=map[Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X-Ua-Compatible:[IE=Edge,chrome=1]] response_status=\"201\"");
		assertTrue(result);
	}
	
	@Test
	public void isWebhookTestInvalid() {
		boolean result = verifyLog
				.isWebhook("level=info response_body=\"\" request_to=\"https://grotesquemoon.de\" response_status=\"201\"");
		assertFalse(result);
	}

	@Test
	public void swapStringTest() {
		String result = verifyLog.swapString("x%y");
		assertEquals("y - x", result);
	}

	@Test
	public void getStatisticsRequestTo() throws IOException{
		String expected = " https://eagerhaystack.com - 750 \n" 
				+ " https://surrealostrich.com.br - 734 \n"
				+ " https://grimpottery.net.br - 732 \n";
		
		List<String> log = readFile.lineToString("src/log.txt");
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "requestTo", 3);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void getStatisticsResponseStatus() throws IOException{
		String expected = " 404 - 1474 \n"
						+ " 503 - 1451 \n"
						+ " 400 - 1440 \n"
						+ " 500 - 1428 \n"
						+ " 200 - 1417 \n"
						+ " 201 - 1402 \n"
						+ " 204 - 1388 \n"; 
		
		List<String> log = readFile.lineToString("src/log.txt");
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "status", 0);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void getStatisticsRequestToMin() throws IOException{
		String expected = " https://woodenoyster.com.br - 2 \n" 
						+ " https://grimpottery.net.br - 2 \n"
						+ " https://surrealostrich.com.br - 1 \n";
			
		List<String> log = readFile.lineToString("src/testMin.txt");
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "requestTo", 3);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void getStatisticsResponseStatusMin() throws IOException{
		String expected = " 400 - 3 \n"
						+ " 500 - 2 \n"
						+ " 404 - 2 \n"
						+ " 503 - 1 \n"
						+ " 201 - 1 \n"
						+ " 200 - 1 \n"; 
			
		List<String> log = readFile.lineToString("src/testMin.txt");		
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "status", 0);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void getStatisticsRequestToMax() throws IOException{
		String expected = " https://eagerhaystack.com - 1500 \n" 
						+ " https://surrealostrich.com.br - 1468 \n"
						+ " https://grimpottery.net.br - 1464 \n";
				
		List<String> log = readFile.lineToString("src/testMax.txt");
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "requestTo", 3);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void getStatisticsResponseStatusMax() throws IOException{
		String expected = " 404 - 2948 \n"
						+ " 503 - 2902 \n"
						+ " 400 - 2880 \n"
						+ " 500 - 2856 \n"
						+ " 200 - 2834 \n"
						+ " 201 - 2804 \n"
						+ " 204 - 2776 \n"; 
				
		List<String> log = readFile.lineToString("src/testMax.txt");
		List<Webhook> listWebhooks = verifyLog.getWebhook(log);
		String result = verifyLog.getStatistics(listWebhooks, "status", 0);
		
		assertEquals(expected, result);
	}
}
