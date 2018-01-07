package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.config.GameConfig;

import java.util.Iterator;

public interface SteppingDirectionStrategy {

    public abstract boolean appliesTo(GameConfig.SteppingDirection direction);

    public abstract Iterator<Integer> getIterator(Move move, int house);

}
