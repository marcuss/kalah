package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.engine.config.GameConfig;

public interface SteppingDirectionStrategy {

    public abstract boolean appliesTo(GameConfig.SteppingDirection direction);

}
