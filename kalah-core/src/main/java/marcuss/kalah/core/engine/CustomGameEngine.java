package marcuss.kalah.core.engine;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Board.Element;
import marcuss.kalah.core.domain.Move;

import java.util.Iterator;

import static marcuss.kalah.core.domain.Board.*;

public class CustomGameEngine extends GameEngine {

    @Override
    protected Move doMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        board.setBoardIterator(
                steppingStrategy.getIterator(move, house)
        );

        Integer sowingSeeds = null;

        Iterator<Element> iterator = board.getBoardIterator();
        Element element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (sowingSeeds == null) {
                sowingSeeds = element.getValue();
                element.setValue(0);
            } else {
                element.setValue(element.getValue() + 1);
                sowingSeeds--;
                if (sowingSeeds <= 0) {
                    break;
                }
            }
        }
        //Todo: use the capture strategy.
        captureStrategy.capture(
                element,
                getOppositeElement(element, move),
                getPlayerStore(move),
                move.getTurn()
                );

        return move;
    }

    private Element getOppositeElement(Element element, Move move) {
        if (!(element instanceof House)){
            return  null;
        }
        switch (element.getOwner()) {
            case PLAYER1:
                return move.getCurrentBoard().getHouses2().get(((House)element).getPos());
            case PLAYER2:
                return move.getCurrentBoard().getHouses1().get(((House)element).getPos());
            default:
                return null;
        }
    }

    //Is becoming increasingly apparent that elements should be grouped by player in the board.
    //TODO: refactor this to be part of the board structure.
    private Element getPlayerStore(Move move) {
        switch (move.getTurn()) {
            case PLAYER1:
                return move.getCurrentBoard().getStore1();
            case PLAYER2:
                return move.getCurrentBoard().getStore2();
            default:
                return null;
        }
    }
}
