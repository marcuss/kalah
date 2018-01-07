package marcuss.kalah.core;

import lombok.Data;
import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.helpers.BoardInitializer;

import static marcuss.kalah.core.domain.Move.State.STARTED;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER1;

@Data
public abstract class Game {

    private GameEngine engine;

    private Board board;

    private Move currentMove;

    public static Game startGame(GameConfig config) {
        if (config.equals(GameConfig.DEFAULT)) {
            return new AwariGame(config);
        } else {
            return new CustomGame(config);
        }
    }

    public Game(GameConfig config) {
        engine = makeEngine(config);
        board = BoardInitializer.initKalah(config.getHouses(), config.getSeeds());
        currentMove = Move.builder()
                .currentBoard(board)
                .state(STARTED)
                .turn(PLAYER1)
                .build();
    }

    protected abstract GameEngine makeEngine(GameConfig config);
}
