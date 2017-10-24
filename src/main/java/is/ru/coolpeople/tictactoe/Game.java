package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */
import java.util.Queue;

public class Game {
	//Todo: Implement game class
    private Board board;
    private Queue<Player> players; // The current player is always first in the Queue
    
    public Game (Queue<Player> playersQueue) {
        board = new Board();
        players = playersQueue;
    }
    
    // Returns false if the cell has already been marked, otherwise, markes the cell
    public boolean doTurn (int index) {
        if(board.getSymbolAtIndex(index) != null) {
            return false;
        }
        
        board.placeSymbol( players.peek().getSymbol(), index);
        players.add( players.poll() ); //changes the current player
        return true;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public String currentPlayerName()
    {
        return (players.peek()).getName();
    }

    
}
