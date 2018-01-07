package marcuss.kalah.core.engine;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Board.Element;
import marcuss.kalah.core.domain.Move;

import java.util.Iterator;

public class CustomGameEngine extends GameEngine {

    @Override
    protected Move doMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        board.setBoardIterator(
                steppingStrategy.getIterator(move, house)
        );

        Integer sowingSeeds = null;

        Iterator<Element> iterator = board.getBoardIterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
                if (sowingSeeds == null){
                    sowingSeeds = element.getValue();
                    element.setValue(0);
                } else {
                    element.setValue(element.getValue()+1);
                    sowingSeeds--;
                    if (sowingSeeds<=0) {
                        break;
                    }
                }
        }
        //Todo: use the capture strategy.

        return move;
    }
}
