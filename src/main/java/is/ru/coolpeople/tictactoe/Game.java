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
    private String lastSymbol;

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
        lastValidMove = index;
        lastSymbol = players.peek().getSymbol();
        board.placeSymbol(lastSymbol, index);
        players.add(players.poll()); //changes the current player

        return isGameOver() ? TurnResult.gameOver : TurnResult.valid;
    }

    public Board getBoard() {
        return board;
    }

    public int getLastValidMove() {
        return lastValidMove;
    }

    public String getLastSymbol() {
        return lastSymbol;
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

        int lastColumn = (lastValidMove % board.getWidth());
        int lastRow = (int)Math.floor(lastValidMove / board.getHeight());

        int bWidth = board.getWidth();
        int bHeight = board.getHeight();
        int maxIndex = (bWidth * bHeight) - 1;

        int combo = 0;

        //checks if game is won by horizontal lines
        for (int i = 0; i < bWidth; i++) {
            if(Objects.equals(board.getSymbolAtIndex((lastRow * bWidth) + i), lastSymbol)){
                combo++;
                if(combo >= winCondition){
                    return true;
                }
            }
            else{
                combo = 0;
            }
        }

        combo = 0;
        //check if game is won by vertical lines
        for (int i = 0; i < bHeight; i++) {
            if(Objects.equals(board.getSymbolAtIndex(lastColumn + (i * bWidth)), lastSymbol)){
                combo++;
                if(combo >= winCondition){
                    return true;
                }
            }
            else{
                combo = 0;
            }
        }
        if(combo >= winCondition){
            return true;
        }


        combo = 0;

        int leftDownMostPos = Integer.MAX_VALUE;
        int leftUptMostPos = Integer.MAX_VALUE;

        int lastPos = lastValidMove;
        int nextPos = lastValidMove;

        //find the most left and bottom tile
        while(true){
                lastPos = nextPos;
                nextPos = lastPos + (bWidth-1);

                if(nextPos > maxIndex || Math.floor(lastPos / bHeight) != Math.floor(nextPos / bHeight)){
                    break;
                }

                leftDownMostPos = nextPos;
        }

        lastPos = leftDownMostPos == Integer.MAX_VALUE ? lastValidMove : leftDownMostPos;
        nextPos = lastPos;
        combo = Objects.equals(board.getSymbolAtIndex(nextPos), lastSymbol) ? 1 : 0;
        //check if game is won diagonally from bottom left to top right
        while(true){
                lastPos = nextPos;
                nextPos = lastPos - (bWidth-1);

                if(nextPos >= 0 && Objects.equals(board.getSymbolAtIndex(nextPos), lastSymbol)){
                    combo++;
                    if(combo >= winCondition){
                        return true;
                    }
                }else{
                    combo = 0;
                }

                if(nextPos < 0 &&  Math.floor(lastPos / bHeight) != Math.floor(nextPos / bHeight)){
                    break;
                }
        }


        lastPos = lastValidMove;
        nextPos = lastValidMove;
        //find the most left and top tile
        while(true){
                lastPos = nextPos;
                nextPos = lastPos - (bWidth+1);

                if(nextPos < 0 || Math.floor(lastPos / bHeight) == Math.floor(nextPos / bHeight) + 2){
                    break;
                }

                leftUptMostPos = nextPos;
        }

        lastPos = leftUptMostPos == Integer.MAX_VALUE ? lastValidMove : leftUptMostPos;
        nextPos = lastPos;
        combo = Objects.equals(board.getSymbolAtIndex(nextPos), lastSymbol) ? 1 : 0;
        //check if game is won diagonally from top left to bottom right
        while(true){

                lastPos = nextPos;
                nextPos = lastPos + (bWidth+1);

                if(nextPos <= maxIndex && Objects.equals(board.getSymbolAtIndex(nextPos), lastSymbol)){
                    combo++;
                    if(combo >= winCondition){
                        return true;
                    }
                }else{
                    combo = 0;
                }

                if(nextPos >= maxIndex &&  Math.floor(lastPos / bHeight) != Math.floor(nextPos / bHeight)){
                    break;
                }
        }

        return false;
    }

}
