package is.ru.coolpeople.tictactoe;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class GameController {
    static HashMap<String, Game> games;

    @RequestMapping(value = "/doTurn")
    public void doTurn(@RequestParam("name") Integer slot,
                       HttpSession session) {
        Game game = games.get(session.getId());

        if (game == null) {
            throw new IllegalStateException("No game created on this session");
        }

        game.doTurn(slot);
    }

    @PostMapping(value = "/newGame", headers = "Accept=application/json", consumes="application/json")
    public void newGame(@RequestBody NewGameWrapper wrapper, HttpSession session) {
        Game game = new Game(wrapper.getPlayers());
        games.put(session.getId(), game);
    }

}
