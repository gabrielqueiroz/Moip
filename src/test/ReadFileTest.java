package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ReadFile;

public class ReadFileTest {
	
	private ReadFile readFile;
	
	@Before
	public void initialize() {
		readFile = new ReadFile();
	}

	@Test(expected = FileNotFoundException.class)
	public void lineToStringTestException() throws IOException {			
		List<String> log = readFile.lineToString("src/moip.txt");
		assertFalse(!log.isEmpty());
	}
	
	@Test
	public void lineToStringTest() throws IOException {				
		List<String> expected = new ArrayList<String>();
		expected.add("level=info "
				+ "response_body=\"\" "
				+ "request_to=\"https://grotesquemoon.de\" "
				+ "response_headers=map[Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X-Ua-Compatible:[IE=Edge,chrome=1]] "
				+ "response_status=\"201\"");
		List<String> result = readFile.lineToString("src/testFile.txt");
		assertEquals(expected, result);
	}

}
