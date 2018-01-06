package marcuss.kalah.core.domain;

public class Result {

    public static enum State {
        STARTED, FINISHED
    }

    public static enum Turn {
        CONTINUE, CHANGE
    }

    private Board currentBoard;

    private State state;

    private Turn turn;

}