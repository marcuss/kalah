package marcuss.kalah.core;

import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.AwariGameEngine;
import marcuss.kalah.core.engine.CustomGameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.engine.strategies.CountRemainingScoreStrategy;
import marcuss.kalah.core.engine.strategies.CounterClockWiseStepping;
import marcuss.kalah.core.engine.strategies.RegularCaptureStratey;
import marcuss.kalah.core.exception.InvalidMove;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static marcuss.kalah.core.domain.Move.State.STARTED;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {

    public final GameConfig CUSTOM_GAME_CONFIG = GameConfig.builder()
            .countRemainingSeeds(true)
            .direction(GameConfig.SteppingDirection.COUNTER_CLOCKWISE)
            .emptyCapture(false)
            .houses(6)
            .seeds(6)
            .pieRuleActive(false)
            .build();

    @Test
    public void testStartDefaultGame() {
        Game game = Game.startGame(GameConfig.DEFAULT);

        assertEquals(
                AwariGameEngine.class,
                game.getEngine().getClass()
        );
    }

    @Test
    public void testStartCustomGame() {
        Game game = Game.startGame(
                CUSTOM_GAME_CONFIG
        );

        assertEquals(
                CustomGameEngine.class,
                game.getEngine().getClass()
        );

        assertEquals(
                CounterClockWiseStepping.class,
                game.getEngine().getSteppingStrategy().getClass()
        );

        assertEquals(
                RegularCaptureStratey.class,
                game.getEngine().getCaptureStrategy().getClass()
        );

        assertEquals(
                CountRemainingScoreStrategy.class,
                game.getEngine().getScoringStrategy().getClass()
        );

        assertEquals(
                STARTED,
                game.getCurrentMove().getState()
        );

        assertEquals(
                PLAYER1,
                game.getCurrentMove().getTurn()
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 10})
    public void firstMoveCustomGame_ErrorInvalidHouses(int house) throws InvalidMove {
        Game game = Game.startGame(
                CUSTOM_GAME_CONFIG
        );
        try {
            game.move(house);
            fail("No exception thrown.");
        } catch (InvalidMove im) {
        }

    }

    @Test
    public void test1stMoveSucceed() {
        Game game = Game.startGame(
                CUSTOM_GAME_CONFIG
        );
        Move currentMove = game.move(3);

        assertNotNull(currentMove);

        assertEquals(
                new Integer(0),
                game.getBoard().getHouses1().get(3).getValue()
        );

        assertEquals(
                new Integer(7),
                game.getBoard().getHouses1().get(2).getValue()
        );

        assertEquals(
                new Integer(7),
                game.getBoard().getHouses1().get(1).getValue()
        );

        assertEquals(
                new Integer(7),
                game.getBoard().getHouses1().get(0).getValue()
        );

        assertEquals(
                new Integer(1),
                game.getBoard().getStore1().getValue()
        );


        assertEquals(
                new Integer(7),
                game.getBoard().getHouses2().get(0).getValue()
        );

        assertEquals(
                new Integer(7),
                game.getBoard().getHouses2().get(1).getValue()
        );

        assertEquals(
                new Integer(6),
                game.getBoard().getHouses2().get(2).getValue()
        );
    }

    @Test
    public void testCapture() {
        Game game = Game.startGame(GameConfig.DEFAULT); //3 seeds
        game.move(0); //Player1, house 0 now is empty
        assertEquals(
                new Integer(0),
                game.getBoard().getHouses1().get(0).getValue()
        );
        assertEquals(
                new Integer(1),
                game.getBoard().getStore1().getValue()
        );
        game.move(5); //Player2, house 5 empty

        game.move(3); // I t should land in the empty 0 house and capture 3 Player2 seeds.

        assertEquals(
                new Integer(7), //1 from the first move, 1 landing seed, and 5 in the rival opposite house.
                game.getBoard().getStore1().getValue()
        );


    }
}
