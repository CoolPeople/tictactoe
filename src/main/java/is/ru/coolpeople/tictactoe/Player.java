package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */

public class Player {
	public String name;
	public String symbol;

	public Player() {
	}

	//function can only get inputs that are deemed acceptible
	public Player(String name, String symbol) {
        this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}

	public void setName(String playerName) {
		this.name = playerName;
	}
	public void setSymbol(String playerSymbol) {
		this.symbol = playerSymbol;
	}
}
