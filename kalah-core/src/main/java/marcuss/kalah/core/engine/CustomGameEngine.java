package marcuss.kalah.core.engine;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Board.Element;
import marcuss.kalah.core.domain.Move;

public class CustomGameEngine extends GameEngine {

    @Override
    protected Move doMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        board.setBoardIterator(
                steppingStrategy.getIterator(move, house)
        );

        Integer sowingSeeds = null;
        //Todo: use the capture strategy.
        for (Element element : board) {
                if (sowingSeeds == null){
                    sowingSeeds = element.getValue();
                    element.setValue(0);
                } else {
                    element.setValue(element.getValue()-1);
                    sowingSeeds--;
                    if (sowingSeeds<=0) {
                        break;
                    }
                }
        }

        return move;
    }
}
