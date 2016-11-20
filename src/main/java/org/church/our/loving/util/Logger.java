package org.church.our.loving.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class Logger {

	public Logger() {
		init(0);
	}
	
	public Logger(int id) {
		init(id);
	}

	private void init(int id) {
		switch (id) {
		case 2:
			try {
				System.setOut(new PrintStream(new File("debug.log")));
				System.setErr(new PrintStream(new File("error.log")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	
	public void debug(Object message) {
		System.out.println("DEBUG [" + new Date() + "] -- " + message);
	}
	
	public void error(String message) {
		System.err.println("ERROR [" + new Date() + "] -- " + message);
	}
	
	public void error(String message, Exception e) {
		StringWriter sWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(sWriter));
		System.err.println("ERROR [" + new Date() + "] -- " + message + "\n Stack: \n" + sWriter);
	}
}
