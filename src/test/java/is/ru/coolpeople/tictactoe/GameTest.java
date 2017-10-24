package is.ru.coolpeople.tictactoe;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

/**
 * Created by olafur on 22/10/2017.
 */

//@SpringBootTest
public class GameTest {
    
    @Test
    public void testMakesGame ()
    {
        Player p1 = new Player ("Anna", "X");
        Player p2 = new Player ("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);
        assertTrue(g != null);
    }
    
    @Test
    public void testPlayers ()
    {
        Player p1 = new Player ("Anna", "X");
        Player p2 = new Player ("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);
        assertEquals("Anna", g.currentPlayerName());
        assertEquals("Anna", g.currentPlayerName());

    }
    
    
    @Test
    public void testDoTurn ()
    {
        Player p1 = new Player ("Anna", "X");
        Player p2 = new Player ("Hafsteinn", "O");
        Queue<Player> players = new ArrayBlockingQueue<Player>(2);
        players.add(p1);
        players.add(p2);
        Game g = new Game(players);
        
        assertEquals("Anna", g.currentPlayerName());
        assertEquals(true, g.doTurn(0)); //Anna
        assertEquals("Hafsteinn", g.currentPlayerName());
        assertEquals(true, g.doTurn(1)); //Hafsteinn
        assertEquals("Anna", g.currentPlayerName());
        assertEquals(false, g.doTurn(1)); //Anna
        assertEquals("Anna", g.currentPlayerName());
        assertEquals(true, g.doTurn(2)); //Anna
        assertEquals("Hafsteinn", g.currentPlayerName());
        assertEquals(false, g.doTurn(1)); //Hafsteinn
        
        Board b = g.getBoard();
        String[] grid = b.getGrid();
        
        assertTrue(grid[0] == "X");
        assertTrue(grid[1] == "O");
        assertTrue(grid[2] == "X");
    }
    

}
