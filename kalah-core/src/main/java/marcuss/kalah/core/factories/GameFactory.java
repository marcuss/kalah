package marcuss.kalah.core.factories;

import marcuss.kalah.core.CustomGame;
import marcuss.kalah.core.Game;
import marcuss.kalah.core.engine.CustomGameEngine;
import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.helpers.StrategyChooserHelper;

public class GameFactory {

    public static Game createCustomGame(GameConfig config) {
        CustomGame game = new CustomGame(config);
        GameEngine engine = new CustomGameEngine();
        StrategyChooserHelper.setSteppingDirectionStrategy(config.getDirection(), engine);
        StrategyChooserHelper.setCaptureStrategy(config.getEmptyCapture(), engine);
        StrategyChooserHelper.setScoringStrategy(config.getCountRemainingSeeds(), engine);
        game.setEngine(engine);
        return game;
    }


}
