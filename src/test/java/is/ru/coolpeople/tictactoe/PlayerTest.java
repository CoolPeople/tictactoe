package is.ru.coolpeople.tictactoe;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by olafur on 22/10/2017.
 */

public class PlayerTest {

	@Test
	public void symbolTest1() {

		Player p = new Player("Anna", "123456789");
		assertEquals("123456789", p.getSymbol());
	}

	@Test
	public void symbolTest2() {
		
		Player p = new Player("Helgi", "X");
		assertEquals("X", p.getSymbol());
	}
    
    	@Test
    	public void nameTest1() {
        	Player p = new Player("Frikki","X");
        	assertEquals("Frikki", p.getName());
    	}
}
