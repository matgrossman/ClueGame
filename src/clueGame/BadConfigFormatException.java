/*
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;

import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {

	public BadConfigFormatException() {
		super("Error: Error in config files.");
	}

	public BadConfigFormatException(String msg) {
		super(msg);

		try {
			PrintWriter toLog = new PrintWriter("data/log.txt");
			toLog.println(msg);
			toLog.close();
		}catch(Exception e) {
			System.out.println("Error: log.txt not found");
		}
	}

}
