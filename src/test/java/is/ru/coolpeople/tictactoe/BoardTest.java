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
}