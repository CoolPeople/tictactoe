package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */
public class Board {
	//todo: implement board model
	//
	String[] grid;

	Board() {
		grid = new String[9];
	}

	String[] getGrid() {
		return grid;
	}

	void placeSymbol(String symbol) {
		//todo: implement
	}

	char getSymbolAtIndex(int index) {
		//todo: implement
		return ' ';
	}
}
