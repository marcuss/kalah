package marcuss.kalah.core.domain;

public class MoveResult {

    public static enum State {
        STARTED, RUNNING, FINISHED
    }

    public static enum Turn {
        CONTINUE, CHANGE
    }

    private Board currentBoard;

    private State state;

    private Turn turn;

}