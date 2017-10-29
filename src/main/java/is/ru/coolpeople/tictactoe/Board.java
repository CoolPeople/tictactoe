package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */
public class Board {
	String[] grid;
	final int minWidth = 3;
	final int minHeight = 3;
	final int minSize = minWidth*minHeight;

	Board() {
		grid = new String[minSize];
	}

	Board(int w, int h) {
	    if(w < minWidth || h < minHeight){
            throw new IllegalArgumentException("Invalid board size, height and width must be 3+");
	    }
		grid = new String[w*h];
	}

	String[] getGrid() {
		return grid;
	}

	void placeSymbol(String symbol, int index) {
		if (index >= grid.length || index < 0) {
			throw new IllegalArgumentException("Index not between 0 and 9");
		}
		grid[index] = symbol;
	}

	String getSymbolAtIndex(int index) {
		if (index >= grid.length || index < 0) {
			throw new IllegalArgumentException("Index not between 0 and 9");
		}
		return grid[index];
	}
}
