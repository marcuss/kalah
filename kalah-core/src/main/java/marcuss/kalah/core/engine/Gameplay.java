package marcuss.kalah.core.engine;

import marcuss.kalah.core.engine.config.GameConfig;

public interface Gameplay {

    default Gameplay initialzeGameplay(GameConfig config) {
        if (config.equals(GameConfig.DEFAULT)) {
            return new AwariGamePlay();
        }
        return new AwariGamePlay();
    }


}
