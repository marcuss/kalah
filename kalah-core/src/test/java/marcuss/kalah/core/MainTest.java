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
    }
}
