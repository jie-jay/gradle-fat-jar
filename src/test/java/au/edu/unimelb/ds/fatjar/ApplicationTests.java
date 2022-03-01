package au.edu.unimelb.ds.fatjar;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import au.edu.unimelb.ds.fatjar.Application;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ApplicationTests {

	private ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private PrintStream ps = new PrintStream(baos);

	@Before
	public void setup() {
		System.setOut(ps);
	}

	@Test
	public void shouldPrintTimeToConsole() {
		Application.main(new String[] { });

		assertThat(output(), containsString("The current local time is"));
	}

	private String output() {
		return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}
}
