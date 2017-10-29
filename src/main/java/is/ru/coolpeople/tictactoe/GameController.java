package is.ru.coolpeople.tictactoe;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class GameController {
    static HashMap<String, Game> games;

    public GameController() {
        games = new HashMap<>();
    }

    @RequestMapping(value = "/doTurn")
    public void doTurn(@RequestParam("slot") Integer slot,
                       HttpSession session) {
        Game game = games.get(session.getId());

        if (game == null) {
            throw new IllegalStateException("No game created on this session");
        }

        game.doTurn(slot);
    }

    @PostMapping(value = "/newGame", headers = "Accept=application/json", consumes="application/json")
    public void newGame(@RequestBody NewGameWrapper wrapper, HttpSession session) {
        Game game = new Game(wrapper.getPlayers(), wrapper.getBoardWidth(), wrapper.getBoardHeight());
        game.setWinCondition(wrapper.getWinCondition());
        games.put(session.getId(), game);
    }

    @GetMapping(value = "/isGameOver")
    public boolean isGameOver(HttpSession session) {
        Game game = games.get(session.getId());

        if (game == null) {
            throw new IllegalStateException("No game created on this session");
        }

        return game.isGameOver();
    }

}
