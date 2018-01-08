package marcuss.kalah.web;

import marcuss.kalah.core.Game;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.web.marcuss.kalah.web.domain.SerializableGame;
import marcuss.kalah.web.marcuss.kalah.web.domain.SerializableMove;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kalah")
@EnableAutoConfiguration
public class KalahController {

    private Game game = Game.startGame(GameConfig.DEFAULT);

    @RequestMapping(method = RequestMethod.POST, value = "/config")
    public SerializableGame startGame(
            @RequestParam(value = "houses", required = true, defaultValue = "6") int houses,
            @RequestParam(value = "seeds", required = true, defaultValue = "3") int seeds) {
        game = Game.startGame(
                GameConfig.builder()
                        .houses(houses)
                        .seeds(seeds)
                        .countRemainingSeeds(true)
                        .direction(GameConfig.SteppingDirection.COUNTER_CLOCKWISE)
                        .emptyCapture(false)
                        .build());

        SerializableGame sGame = new SerializableGame();
        sGame.setFields(game);
        return sGame;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play")
    public SerializableMove move(@RequestBody SerializableMove requestMove) {
        Move responseMove = null;
        if (requestMove == null) {
            responseMove = game.move(requestMove.getHouse());
        } else {
            responseMove = game.move(requestMove.getHouse(),
                    SerializableMove.rebuildMove(requestMove, game.getEngine(), requestMove.getHouse())
            );
        }
        SerializableMove sMove = new SerializableMove();
        sMove.setFields(responseMove);
        return sMove;
    }
}
