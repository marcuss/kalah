package marcuss.kalah.core.engine;

import marcuss.kalah.core.domain.Element;
import marcuss.kalah.core.domain.Move;

public class CustomGameEngine extends GameEngine {

    @Override
    protected Move doMove(Move move, Element element) {
        return move;
    }


}
