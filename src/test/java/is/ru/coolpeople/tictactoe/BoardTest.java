package is.ru.coolpeople.tictactoe;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

/**
 * Created by olafur on 22/10/2017.
 */

@SpringBootTest
public class BoardTest {
	@Test
	public void placeSymbol() throws Exception {
		//initialize new board
		Board board = new Board();

		//place an arbitrary symbol correctly
		board.placeSymbol("X", 0);

		//check that correctly placed symbol got where it was headed
		assertTrue("X".equals(board.grid[0]));

		//place symbol incorrectly
		boolean caughtException = false;

		try {
			board.placeSymbol("X", 123);
		} catch (IllegalArgumentException e) {
			caughtException = true;
		}

		//IllegalArgumentException should be thrown since this is out of bounds
		assert(caughtException);
	}

	@Test
	public void getSymbolAtIndex() throws Exception {
		//initialize new board
		Board board = new Board();
		board.placeSymbol("X", 4);

		String symbol = board.getSymbolAtIndex(4);

		//check that we get the expected symbol
		assertEquals("X", symbol);

		boolean caughtException = false;
		try {
			board.getSymbolAtIndex(10);
		} catch (IllegalArgumentException e) {
			caughtException = true;
		}

		assertTrue(caughtException);
	}

	@Test
	public void getGrid() throws Exception {
		Board board = new Board();
		String[] grid = board.getGrid();

		assertFalse(grid == null);
	}

	@Test
	public void testBoard() {
		//Set up new board
		Board board = new Board();

		//Grid should be initialized
		assertFalse(board.grid == null);
	}

    @Test
	public void testCustomBoard() {
		//Set up new board
		Board board = new Board(5,5);

		//Grid should be initialized and have 25 tiles
		assertFalse(board.grid == null);
		assertEquals(25, board.grid.length);
	}

    @Test
	public void testInvalidBoardSize() {
		//Set up new board with invalid size
		boolean caughtExceptionOne = false;
		boolean caughtExceptionTwo = false;
		try {
			Board board = new Board(1,2);
		} catch (IllegalArgumentException e) {
			caughtExceptionOne = true;
		}

		try {
			Board board = new Board(3,2);
		} catch (IllegalArgumentException e) {
			caughtExceptionTwo = true;
		}

		//should catch exception
		assertTrue(caughtExceptionOne && caughtExceptionTwo);
	}

}
