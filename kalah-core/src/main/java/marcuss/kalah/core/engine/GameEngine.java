package marcuss.kalah.core.engine;

import lombok.Data;
import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Board.Store;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.strategies.CaptureStrategy;
import marcuss.kalah.core.engine.strategies.ScoringStrategy;
import marcuss.kalah.core.engine.strategies.SteppingDirectionStrategy;
import marcuss.kalah.core.exception.InvalidMove;

import java.util.Iterator;

@Data
public abstract class GameEngine {

    protected SteppingDirectionStrategy steppingStrategy;

    protected CaptureStrategy captureStrategy;

    protected ScoringStrategy scoringStrategy;

    public Move move(Move move, int house) {
        validateMove(move, house);
        Board.Element element = sowSeeds(move, house);
        captureLastPosition(move, element);
        defineNextTurn(move, element);
        return  doMove(move, element);
    }

    private void defineNextTurn(Move move, Board.Element element) {
        if (! (element instanceof Store)) {
            switch (move.getTurn()) {
                case PLAYER1:
                    move.setTurn(Move.Turn.PLAYER2);
                    break;
                case PLAYER2:
                    move.setTurn(Move.Turn.PLAYER1);
                    break;
            }
        }
    }

    private Board.Element sowSeeds(Move move, int house) {
        Board board = move.getCurrentBoard();
        board.setBoardIterator(
                steppingStrategy.getIterator(move, house)
        );

        Integer sowingSeeds = null;
        Iterator<Board.Element> iterator = move.getCurrentBoard().getBoardIterator();
        Board.Element element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (sowingSeeds == null) {
                sowingSeeds = element.getSeeds();
                element.setSeeds(0);
            } else {
                element.setSeeds(element.getSeeds() + 1);
                sowingSeeds--;
                if (sowingSeeds <= 0) {
                    break;
                }
            }
        }
        return element;
    }

    private void captureLastPosition(Move move, Board.Element element) {
        captureStrategy.capture(
                element,
                getOppositeElement(element, move),
                getPlayerStore(move),
                move.getTurn()
        );
    }

    private void validateMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        if (house < 0 || house > board.getHouses1().size()) throw new InvalidMove("Invalid house number.");
    }

    // extension point, unused as for now.
    protected Move doMove(Move move, Board.Element element) {
        return move;
    }


    private Board.Element getOppositeElement(Board.Element element, Move move) {
        if (!(element instanceof Board.House)) {
            return null;
        }
        switch (element.getOwner()) {
            case PLAYER1:
                return move.getCurrentBoard().getHouses2().get(((Board.House) element).getPos());
            case PLAYER2:
                return move.getCurrentBoard().getHouses1().get(((Board.House) element).getPos());
            default:
                return null;
        }
    }

    //Is becoming increasingly apparent that elements should be grouped by player in the board.
    //TODO: refactor this to be part of the board structure.
    private Board.Element getPlayerStore(Move move) {
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
