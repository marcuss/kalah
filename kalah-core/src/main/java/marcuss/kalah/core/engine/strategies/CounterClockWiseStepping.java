package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.engine.config.GameConfig;


public class CounterClockWiseStepping implements SteppingDirectionStrategy {
    //todo: improve as necessary
    private static CounterClockWiseStepping instance = new CounterClockWiseStepping();

    public boolean appliesTo(GameConfig.SteppingDirection direction) {
        return direction.equals(GameConfig.SteppingDirection.COUNTER_CLOCKWISE);
    }
    // todo: Find a way to force all the implementors to be singletons (best with out spring)
    public static SteppingDirectionStrategy getInstance() {
        return instance;
    }
}
