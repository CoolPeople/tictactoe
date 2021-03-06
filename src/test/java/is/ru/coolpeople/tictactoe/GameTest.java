package is.ru.coolpeople.tictactoe;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.*;

/**
 * Created by olafur on 22/10/2017.
 */

@SpringBootTest
public class GameTest {

    @Test
    public void testMakesGame() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);
        assertTrue(g != null);
    }

    @Test
    public void testMakesCustomGameSize() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        int boardWith = 3;
        int boardHeight = 5;
        Game g = new Game(players, boardWith, boardHeight);
        assertTrue(g != null);
        assertEquals((boardWith * boardHeight), g.getBoard().getGrid().length);
    }

    @Test
    public void testDefaultWinConditionValue() {
        Player p1 = new Player("Anna", "X");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        Game g = new Game(players);
        assertTrue(g != null);
        assertEquals(3, g.getWinCondition());
    }

    @Test
    public void testCustomWinConditionValue() {
        Player p1 = new Player("Anna", "X");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        Game g = new Game(players);
        assertTrue(g != null);
        g.setWinCondition(5);
        assertEquals(5, g.getWinCondition());
    }

    @Test
    public void testInvalidWinConditionException() {
        Player p1 = new Player("Anna", "X");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        Game g = new Game(players);
        assertTrue(g != null);

        boolean caughtExceptionOne = false;
        boolean caughtExceptionTwo = false;

		try {
			g.setWinCondition(1);
		} catch (IllegalArgumentException e) {
			caughtExceptionOne = true;
		}

		try {
			g.setWinCondition(2);
		} catch (IllegalArgumentException e) {
			caughtExceptionTwo = true;
		}

        assertTrue(caughtExceptionOne && caughtExceptionTwo);
    }

    @Test
    public void testPlayers() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);
        assertEquals("Anna", g.currentPlayerName());
        assertEquals("Anna", g.currentPlayerName());

    }


    @Test
    public void testDoTurn() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);

        assertEquals("Anna", g.currentPlayerName());
        assertEquals(TurnResult.valid, g.doTurn(0)); //Anna
        assertEquals("Hafsteinn", g.currentPlayerName());
        assertEquals(TurnResult.valid, g.doTurn(1)); //Hafsteinn
        assertEquals("Anna", g.currentPlayerName());
        assertEquals(TurnResult.invalid, g.doTurn(1)); //Anna
        assertEquals("Anna", g.currentPlayerName());
        assertEquals(TurnResult.valid, g.doTurn(2)); //Anna
        assertEquals("Hafsteinn", g.currentPlayerName());
        assertEquals(TurnResult.invalid, g.doTurn(1)); //Hafsteinn

        Board b = g.getBoard();
        String[] grid = b.getGrid();

        assertEquals("X",grid[0]);
        assertEquals("O",grid[1]);
        assertEquals("X",grid[2]);
    }

    @Test
    public void testDoTurnGameOver() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        //Test winning with cells on 0 - 1 - 2, test that do turn returns gameOver enum
        Game g = new Game(players);
        g.doTurn(0); //Anna
        g.doTurn(4); //Hafsteinn
        g.doTurn(1); //Anna
        g.doTurn(5); //Hafsteinn
        assertEquals(TurnResult.gameOver, g.doTurn(2)); //Anna Wins
    }

    @Test
    public void testDoTurnGameOverCustomSizeAndWinCondition() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        //Test winning with cells on 0 - 1 - 2, test that do turn returns gameOver enum
        Game g = new Game(players,5,5);
        g.setWinCondition(5);
        g.doTurn(0); //Anna
        g.doTurn(4); //Hafsteinn
        g.doTurn(6); //Anna
        g.doTurn(9); //Hafsteinn
        g.doTurn(12); //Anna
        assertFalse(g.isGameOver());
        g.doTurn(3); //Hafsteinn
        g.doTurn(18); //Anna
        assertFalse(g.isGameOver());
        g.doTurn(2); //Hafsteinn
        assertEquals(TurnResult.gameOver, g.doTurn(24)); //Anna
        assertTrue(g.isGameOver()); //Anna Wins
    }

    @Test
    public void testIsGameOver() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);

        //Test that if the top horizontal line is all the same symbol, game is over.
        Game g = new Game(players);
        g.doTurn(0); //Anna
        assertFalse(g.isGameOver());
        g.doTurn(4); //Hafsteinn
        assertFalse(g.isGameOver());
        g.doTurn(1); //Anna
        assertFalse(g.isGameOver());
        g.doTurn(5); //Hafsteinn
        assertFalse(g.isGameOver());
        g.doTurn(2); //Anna
        assertTrue(g.isGameOver());

        //Test that if the middle horizontal line is the same symbol, game is over
        Game g2 = new Game(players);
        g2.doTurn(1); //Anna
        assertFalse(g2.isGameOver());
        g2.doTurn(3); //Hafsteinn
        assertFalse(g2.isGameOver());
        g2.doTurn(0); //Anna
        assertFalse(g2.isGameOver());
        g2.doTurn(5); //Hafsteinn
        assertFalse(g2.isGameOver());
        g2.doTurn(8); //Anna
        assertFalse(g2.isGameOver());
        g2.doTurn(4); //Hafsteinn
        assertTrue(g2.isGameOver());

        //Test that if the middle horizontal line is the same symbol, game is over
        Game g3 = new Game(players);
        g3.doTurn(1); //Anna
        assertFalse(g3.isGameOver());
        g3.doTurn(6); //Hafsteinn
        assertFalse(g3.isGameOver());
        g3.doTurn(0); //Anna
        assertFalse(g3.isGameOver());
        g3.doTurn(7); //Hafsteinn
        assertFalse(g3.isGameOver());
        g3.doTurn(5); //Anna
        assertFalse(g3.isGameOver());
        g3.doTurn(8); //Hafsteinn
        assertTrue(g3.isGameOver());

        //Test that the left vertical line has the same symbol in all cells, the game is over
        Game g4 = new Game(players);
        g4.doTurn(0); //Anna
        assertFalse(g4.isGameOver());
        g4.doTurn(4); //Hafsteinn
        assertFalse(g4.isGameOver());
        g4.doTurn(3); //Anna
        assertFalse(g4.isGameOver());
        g4.doTurn(7); //Hafsteinn
        assertFalse(g4.isGameOver());
        g4.doTurn(6); //Anna
        assertTrue(g4.isGameOver());

        //Test that the middle vertical line has the same symbol in all cells, the game is over
        Game g5 = new Game(players);
        g5.doTurn(1); //Anna
        assertFalse(g5.isGameOver());
        g5.doTurn(0); //Hafsteinn
        assertFalse(g5.isGameOver());
        g5.doTurn(4); //Anna
        assertFalse(g5.isGameOver());
        g5.doTurn(2); //Hafsteinn
        assertFalse(g5.isGameOver());
        g5.doTurn(7); //Anna
        assertTrue(g5.isGameOver());

        //Test that the right vertical line has the same symbol in all cells, the game is over
        Game g6 = new Game(players);
        g6.doTurn(1); //Anna
        assertFalse(g6.isGameOver());
        g6.doTurn(2); //Hafsteinn
        assertFalse(g6.isGameOver());
        g6.doTurn(0); //Anna
        assertFalse(g6.isGameOver());
        g6.doTurn(5); //Hafsteinn
        assertFalse(g6.isGameOver());
        g6.doTurn(7); //Anna
        assertFalse(g6.isGameOver());
        g6.doTurn(8); //Hafsteinn
        assertTrue(g6.isGameOver());

        //Test winning with cells 0-4-8
        Game g7 = new Game(players);
        g7.doTurn(0); //Anna
        assertFalse(g7.isGameOver());
        g7.doTurn(1); //Hafsteinn
        assertFalse(g7.isGameOver());
        g7.doTurn(4); //Anna
        assertFalse(g7.isGameOver());
        g7.doTurn(2); //Hafsteinn
        assertFalse(g7.isGameOver());
        g7.doTurn(8); //Anna
        assertTrue(g7.isGameOver());

        //Test winning with cells 2-4-6
        Game g8 = new Game(players);
        g8.doTurn(2); //Anna
        assertFalse(g8.isGameOver());
        g8.doTurn(1); //Hafsteinn
        assertFalse(g8.isGameOver());
        g8.doTurn(4); //Anna
        assertFalse(g8.isGameOver());
        g8.doTurn(0); //Hafsteinn
        assertFalse(g8.isGameOver());
        g8.doTurn(6); //Anna
        assertTrue(g8.isGameOver());
    }

    @Test
    public void testLastValidMove() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        //Test winning with cells on 0 - 1 - 2, test that do turn returns gameOver enum
        Game g = new Game(players);

        g.doTurn(0); //Anna
        assertEquals(0, g.getLastValidMove());
        g.doTurn(4); //Hafsteinn
        assertEquals(4, g.getLastValidMove());
        g.doTurn(0); //Anna
        assertEquals(4, g.getLastValidMove());
        g.doTurn(1); //Anna
        assertEquals(1, g.getLastValidMove());
        g.doTurn(5); //Hafsteinn
        assertEquals(5, g.getLastValidMove());
        g.doTurn(2); //Anna
        assertEquals(2, g.getLastValidMove());
    }

    @Test
    public void testLastSymbol() {
        Player p1 = new Player("Anna", "X");
        Player p2 = new Player("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        //Test winning with cells on 0 - 1 - 2, test that do turn returns gameOver enum

        Game g = new Game(players);
        g.doTurn(0); //Anna
        assertEquals("X", g.getLastSymbol());
        g.doTurn(4); //Hafsteinn
        assertEquals("O", g.getLastSymbol());
        g.doTurn(0); //Anna
        assertEquals("O", g.getLastSymbol());
        g.doTurn(1); //Anna
        assertEquals("X", g.getLastSymbol());
        g.doTurn(5); //Hafsteinn
        assertEquals("O", g.getLastSymbol());
        g.doTurn(2); //Anna
        assertEquals("X", g.getLastSymbol());
    }


}
