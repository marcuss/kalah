package marcuss.kalah.core.engine;

import lombok.Data;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.strategies.SteppingDirectionStrategy;

@Data
public abstract class GameEngine {

    protected SteppingDirectionStrategy steppingStrategy;

    public abstract Move move(Move move);
}
