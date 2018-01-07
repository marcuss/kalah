package marcuss.kalah.core.factories;

import marcuss.kalah.core.engine.CustomGameEngine;
import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.helpers.StrategyChooserHelper;

public class EngineFactory {

    public static GameEngine createCustomGameEngine(GameConfig config) {
        GameEngine engine = new CustomGameEngine();
        StrategyChooserHelper.setSteppingDirectionStrategy(config.getDirection(), engine);
        StrategyChooserHelper.setCaptureStrategy(config.getEmptyCapture(), engine);
        StrategyChooserHelper.setScoringStrategy(config.getCountRemainingSeeds(), engine);
        return engine;
    }

}
