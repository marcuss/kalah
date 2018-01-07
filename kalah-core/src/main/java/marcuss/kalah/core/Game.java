package marcuss.kalah.core;

import lombok.Data;
import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.helpers.BoardInitializer;

@Data
public abstract class Game {

    private GameEngine engine;

    private Board board;

    public Game(GameConfig config){
        engine = initGameEngine();
        board = BoardInitializer.initKalah(config.getHouses(), config.getSeeds());
    }

    public abstract GameEngine initGameEngine();
}
