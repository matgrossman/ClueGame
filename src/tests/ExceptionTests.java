package tests;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import clueGame.BadConfigFormatException;
import clueGame.Board;

public class ExceptionTests {


	// Test that an exception is not thrown for a layout file with correct parameters
	@Test
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		assertDoesNotThrow(() -> {
			Board board = Board.getInstance();
			board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
			// Instead of initialize, we call the two load functions directly.
			// This is necessary because initialize contains a try-catch.
			board.loadSetupConfig();
			board.loadLayoutConfig();
		});
	}

}
