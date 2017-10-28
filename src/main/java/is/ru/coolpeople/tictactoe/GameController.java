package is.ru.coolpeople.tictactoe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@RestController
public class GameController {
    static HashMap<String, Queue<Player>> players;
    static HashMap<String, Game> games;

    @RequestMapping(value = "/doTurn")
    public void doTurn(@RequestParam("name") Integer slot,
                       @RequestParam("symbol") String symbol) {
        //todo: get game from session and call do turn
    }

    @PostMapping(value = "/newPlayer")
    public ResponseEntity<?> newPlayer(@RequestParam("name") String name,
                                       @RequestParam("symbol") String symbol,
                                       HttpSession session){
        Player player = new Player(name, symbol);
        if (players.get(session.getId()) == null) {
            players.put(session.getId(), new ArrayBlockingQueue<>(2));
        }
        players.get(session.getId()).add(player);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping(value = "/newGame")
    public ResponseEntity<?> newGame(HttpSession session){
        //todo: create new game
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
