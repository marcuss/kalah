package marcuss.kalah.core;

import marcuss.kalah.core.engine.AwariGameEngine;
import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;

public class AwariGame extends Game {

    public AwariGame(GameConfig config) {
        super(config);
    }

    @Override
    public GameEngine initGameEngine() {
        return new AwariGameEngine();
    }
}
