package marcuss.kalah.core.engine;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Move;

public class CustomGameEngine extends GameEngine {

    @Override
    protected Move doMove(Move move, Board.Element element) {
        return move;
    }


}
