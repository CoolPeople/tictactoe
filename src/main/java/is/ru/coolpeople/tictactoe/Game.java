package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */

import java.util.Objects;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> players; // The current player is always first in the Queue

    public Game(Queue<Player> playersQueue) {
        board = new Board();
        players = playersQueue;
    }

    // Returns false if the cell has already been marked, otherwise, markes the cell
    public boolean doTurn(int index) {
        if (board.getSymbolAtIndex(index) != null) {
            return false;
        }

        board.placeSymbol(players.peek().getSymbol(), index);
        players.add(players.poll()); //changes the current player
        return true;
    }

    public Board getBoard() {
        return board;
    }

    public String currentPlayerName() {
        return (players.peek()).getName();
    }

    public boolean isGameOver() {
        //checks if game is won by horizontal lines
        for (int i = 0; i < 7; i += 3) {
            if (board.getSymbolAtIndex(i) != null) {
                if (Objects.equals(board.getSymbolAtIndex(i), board.getSymbolAtIndex(i + 1)) &&
                        Objects.equals(board.getSymbolAtIndex(i), board.getSymbolAtIndex(i + 2))) {
                    return true;
                }
            }
        }

        //check if game is won by vertical lines
        for (int i = 0; i < 3; i++) {
            if (board.getSymbolAtIndex(i) != null) {
                if (Objects.equals(board.getSymbolAtIndex(i), board.getSymbolAtIndex(i + 3)) &&
                        Objects.equals(board.getSymbolAtIndex(i), board.getSymbolAtIndex(i + 6))) {
                    return true;
                }
            }
        }

        //check if game is won by corner-to-corner lines
        if (board.getSymbolAtIndex(4) != null) {
            if (Objects.equals(board.getSymbolAtIndex(0), board.getSymbolAtIndex(4)) &&
                    Objects.equals(board.getSymbolAtIndex(4), board.getSymbolAtIndex(8))) {
                return true;
            }

            if (Objects.equals(board.getSymbolAtIndex(6), board.getSymbolAtIndex(4)) &&
                    Objects.equals(board.getSymbolAtIndex(4), board.getSymbolAtIndex(2))) {
                return true;
            }
        }

        return false;
    }

}
