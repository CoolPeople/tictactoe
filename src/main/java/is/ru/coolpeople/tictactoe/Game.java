package is.ru.coolpeople.tictactoe;

/**
 * Created by olafur on 22/10/2017.
 */

import java.util.Objects;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> players; // The current player is always first in the Queue
    private final int minWinCondition = 3;
    private final int minCustomGameWinCondition = 4;
    private int winCondition = minWinCondition;

    private int lastValidMove;

    public Game(Queue<Player> playersQueue) {
        board = new Board();
        players = playersQueue;
    }

    public Game(Queue<Player> playersQueue, int bW, int bH) {
        board = new Board(bW, bH);
        players = playersQueue;
    }

    // Returns false if the cell has already been marked, otherwise, markes the cell
    public TurnResult doTurn(int index) {
        if (board.getSymbolAtIndex(index) != null) {
            return TurnResult.invalid;
        }

        board.placeSymbol(players.peek().getSymbol(), index);
        players.add(players.poll()); //changes the current player
        lastValidMove = index;
        return isGameOver() ? TurnResult.gameOver : TurnResult.valid;
    }

    public Board getBoard() {
        return board;
    }

    public int getLastValidMove() {
        return lastValidMove;
    }

    public String currentPlayerName() {
        return (players.peek()).getName();
    }

    public int getWinCondition(){
        return winCondition;
    }

    public void setWinCondition(int wC){
        if(wC < minWinCondition){
            throw new IllegalArgumentException("Win condition must be at least 3");
	    }
        winCondition = wC;
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
