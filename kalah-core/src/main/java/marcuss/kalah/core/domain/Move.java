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

}