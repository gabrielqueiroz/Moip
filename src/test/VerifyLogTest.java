package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Webhook;

import org.junit.Before;
import org.junit.Test;

import controller.VerifyLog;

public class VerifyLogTest {
	private VerifyLog verifyLog;

	@Before
	public void initialize() {
		verifyLog = new VerifyLog();
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

}
