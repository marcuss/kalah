package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Element;
import marcuss.kalah.core.domain.Move;

public interface CaptureStrategy {

    void capture(Element element, Element oppositeElement, Element playerStore, Move.Turn player);
}
