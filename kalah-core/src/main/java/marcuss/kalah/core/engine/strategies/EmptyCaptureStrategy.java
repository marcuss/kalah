package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Board.Element;
import marcuss.kalah.core.domain.Move;

public class EmptyCaptureStrategy implements CaptureStrategy {

    //todo: improve singleton as necessary
    private static EmptyCaptureStrategy instance = new EmptyCaptureStrategy();

    public static EmptyCaptureStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean emptyCapture) {
        return emptyCapture;
    }

    @Override
    public void capture(Element element, Element oppositeElement, Element playerStore, Move.Turn player) {

    }
}
