package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Move {

    public static enum State {
        STARTED, RUNNING, FINISHED
    }

    public static enum Turn {
        PLAYER1, PLAYER2
    }

    private Board currentBoard;

    private State state;

    private Turn turn;

    public Player getCurrentPlayer() {
        switch (turn) {
            case PLAYER1:
                return currentBoard.getPlayer1();
            case PLAYER2:
                return currentBoard.getPlayer2();
            default:
                return null;
        }
    }

    public Player getOppositePlayer() {
        switch (turn) {
            case PLAYER1:
                return currentBoard.getPlayer2();
            case PLAYER2:
                return currentBoard.getPlayer1();
            default:
                return null;
        }
    }

}