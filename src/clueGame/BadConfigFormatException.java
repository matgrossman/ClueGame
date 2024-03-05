package clueGame;

public class BadConfigFormatException extends Exception {
	
	public BadConfigFormatException() {
		super("Error: Error in config files.");
	}
	
	public BadConfigFormatException(String msg) {
		super(msg);
	}
	
}
