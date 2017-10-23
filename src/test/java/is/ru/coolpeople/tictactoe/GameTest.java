package is.ru.coolpeople.tictactoe;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by olafur on 22/10/2017.
 */

@SpringBootTest
public class GameTest {
    
    @Test
    public void testMakesGame ()
    {
        Game g = new Game();
        assertFalse(g == null);
    }
    
    

}
