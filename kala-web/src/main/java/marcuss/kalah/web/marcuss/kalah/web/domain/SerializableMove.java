package marcuss.kalah.web.marcuss.kalah.web.domain;

import lombok.Data;
import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.domain.Player;
import marcuss.kalah.core.engine.GameEngine;

import java.io.Serializable;

@Data
public class SerializableMove implements Serializable {

    private Player player1;

    private Player player2;

    private Move.State state;

    private Move.Turn turn;

    private int house;

    public void setFields(Move move) {
        switch(move.getTurn()){
            case PLAYER1:
                player1 = move.getCurrentPlayer();
                player2 = move.getOppositePlayer();
                break;
            case PLAYER2:
                player2 = move.getCurrentPlayer();
                player1 = move.getOppositePlayer();
                break;
        }
        state = move.getState();
        turn = move.getTurn();
    }

    public static Move rebuildMove(SerializableMove requestMove, GameEngine engine, int house) {
        Move rebuiltMove = Move.builder()
                .turn(requestMove.turn)
                .state(requestMove.state)
                .currentBoard(
                        Board.builder()
                        .player1(requestMove.player1)
                        .player2(requestMove.player2)
                        .build()
                )
                .build();
        rebuiltMove.getCurrentBoard().setBoardIterator(engine.getSteppingStrategy().getIterator(rebuiltMove, house));
        return rebuiltMove;
    }
}
