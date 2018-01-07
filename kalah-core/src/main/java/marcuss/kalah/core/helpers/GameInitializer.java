package marcuss.kalah.core.helpers;

import marcuss.kalah.core.AwariGame;
import marcuss.kalah.core.CustomGame;
import marcuss.kalah.core.Game;
import marcuss.kalah.core.engine.config.GameConfig;

public class GameInitializer {

    public static Game startGame(GameConfig config) {
        if (config.equals(GameConfig.DEFAULT)) {
            return new AwariGame(config);
        } else {
            return new CustomGame(config);
        }
    }
}
