package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Move;

public interface CaptureStrategy {

    void capture(Board.Element element, Board.Element oppositeElement, Board.Element playerStore, Move.Turn player);
}
