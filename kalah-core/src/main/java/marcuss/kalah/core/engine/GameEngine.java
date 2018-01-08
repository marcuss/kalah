package marcuss.kalah.core.engine;

import lombok.Data;
import marcuss.kalah.core.domain.*;
import marcuss.kalah.core.engine.strategies.CaptureStrategy;
import marcuss.kalah.core.engine.strategies.ScoringStrategy;
import marcuss.kalah.core.engine.strategies.SteppingDirectionStrategy;
import marcuss.kalah.core.exception.InvalidMove;

import java.util.Iterator;

import static marcuss.kalah.core.domain.Move.State.FINISHED;
import static marcuss.kalah.core.domain.Move.State.RUNNING;

@Data
public abstract class GameEngine {

    public SteppingDirectionStrategy steppingStrategy;

    public CaptureStrategy captureStrategy;

    public ScoringStrategy scoringStrategy;

    public Move move(Move move, int house) {
        validateMove(move, house);
        Element element = sowSeeds(move, house);
        if (element instanceof House) {
            captureLastPosition(move, (House) element);
        }

        scoringStrategy.score(move.getCurrentPlayer());
        scoringStrategy.score(move.getOppositePlayer());

        if (isFinished(move)) {
            move.setState(FINISHED);
        } else {
            move.setState(RUNNING);
            defineNextTurn(move, element);
        }
        return move;
    }

    private void validateMove(Move move, int house) {
        Board board = move.getCurrentBoard();
        if (house < 0 || house > board.getPlayer1().getHouses().size()) throw new InvalidMove("Invalid house number.");
    }

    private Element sowSeeds(Move move, int house) {
        Board board = move.getCurrentBoard();
        board.setBoardIterator(
                steppingStrategy.getIterator(move, house)
        );

        Integer sowingSeeds = null;
        Iterator<Element> iterator = move.getCurrentBoard().getBoardIterator();
        Element element = null;
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

    private void captureLastPosition(Move move, House house) {
        captureStrategy.capture(
                house,
                move.getOppositePlayer().getHouses().get(house.getPos()),
                move.getCurrentPlayer().getStore(),
                move.getTurn()
        );
    }

    private boolean isFinished(Move move) {
        if (move.getCurrentPlayer().getHouses().stream().mapToInt(h -> h.getSeeds().intValue()).sum() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void defineNextTurn(Move move, Element element) {
        if (!(element instanceof Store)) {
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
}
