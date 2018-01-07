package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.engine.config.GameConfig;

public class ClockWiseStepping implements SteppingDirectionStrategy {

    //todo: improve as necessary
    private static ClockWiseStepping instance = new ClockWiseStepping();

    public static SteppingDirectionStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(GameConfig.SteppingDirection direction) {
        return direction.equals(GameConfig.SteppingDirection.CLOCKWISE);
    }
}
