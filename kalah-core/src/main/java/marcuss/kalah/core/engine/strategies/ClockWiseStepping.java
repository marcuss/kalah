package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Element;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.config.GameConfig;

import java.util.Iterator;

public class ClockWiseStepping implements SteppingDirectionStrategy {

    //todo: improve singleton as necessary
    private static ClockWiseStepping instance = new ClockWiseStepping();

    public static SteppingDirectionStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(GameConfig.SteppingDirection direction) {
        return direction.equals(GameConfig.SteppingDirection.CLOCKWISE);
    }

    //todo: implement
    @Override
    public Iterator<Element> getIterator(Move move, int house) {
        return null;
    }
}
