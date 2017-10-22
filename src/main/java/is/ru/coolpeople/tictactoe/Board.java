package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */
public class Board {
	String[] grid;

	Board() {
		grid = new String[9];
	}

	String[] getGrid() {
		return grid;
	}

	void placeSymbol(String symbol, int index) {
		if (index >= 9 || index < 0) {
			throw new IllegalArgumentException("Index not between 0 and 9");
		}
		grid[index] = symbol;
	}

	String getSymbolAtIndex(int index) {
		if (index >= 9 || index < 0) {
			throw new IllegalArgumentException("Index not between 0 and 9");
		}
		return grid[index];
	}
}
