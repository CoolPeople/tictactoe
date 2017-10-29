package is.ru.coolpeople.tictactoe;

import java.util.Queue;

/**
 * Created by Olik on 28.10.2017.
 */
public class NewGameWrapper {
    public Queue<Player> players;
    public Integer boardWidth;
    public Integer boardHeight;
    public Integer winCondition;

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public Integer getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(Integer boardWidth) {
        this.boardWidth = boardWidth;
    }

    public Integer getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(Integer boardHeight) {
        this.boardHeight = boardHeight;
    }

    public Integer getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(Integer winCondition) {
        this.winCondition = winCondition;
    }
}
