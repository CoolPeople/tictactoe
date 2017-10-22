package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */

public class Player {
	
	private String playerSymbol = "SymbolMissing";
	//playerName will maybe not be used
	private String playerName;

	//function can only get inputs that are deemed acceptible
	public Player(String symbol) {

		playerSymbol = symbol;
	}

	public String getSymbol() {

		return playerSymbol;
	}

	public String getName() {
		
		return playerName;
	}

}
