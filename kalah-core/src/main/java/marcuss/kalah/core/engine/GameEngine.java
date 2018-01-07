package marcuss.kalah.core.engine;

import lombok.Data;
import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.strategies.CaptureStrategy;
import marcuss.kalah.core.engine.strategies.ScoringStrategy;
import marcuss.kalah.core.engine.strategies.SteppingDirectionStrategy;
import marcuss.kalah.core.exception.InvalidMove;

@Data
public abstract class GameEngine {

    protected SteppingDirectionStrategy steppingStrategy;

    protected CaptureStrategy captureStrategy;

    protected ScoringStrategy scoringStrategy;

    public Move move(Move move, int house) {
        validateMove(move, house);
        return doMove(move, house);
    }

    private void validateMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        if (house < 0 || house > board.getHouses1().size()) throw new InvalidMove("Invalid house number.");
    }

    protected abstract Move doMove(Move move, int house);
}
