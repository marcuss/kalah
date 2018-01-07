package marcuss.kalah.core;

import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.factories.EngineFactory;

public class CustomGame extends Game {

    public CustomGame(GameConfig config) {
        super(config);
    }

    @Override
    public GameEngine makeEngine(GameConfig config) {
        return EngineFactory.createCustomGameEngine(config);
    }
}
