package marcuss.kalah.core;

import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;

public class CustomGame extends Game {

    public CustomGame(GameConfig config) {
        super(config);
    }

    @Override
    public GameEngine initGameEngine() {
        return null;
    }
}
