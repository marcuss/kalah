package marcuss.kalah.core;

import marcuss.kalah.core.engine.AwariGameEngine;
import marcuss.kalah.core.engine.CustomGameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.engine.strategies.CountRemainingScoreStrategy;
import marcuss.kalah.core.engine.strategies.CounterClockWiseStepping;
import marcuss.kalah.core.engine.strategies.RegularCaptureStratey;
import org.junit.Test;

import static marcuss.kalah.core.domain.Move.State.STARTED;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER1;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

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
                GameConfig.builder()
                        .countRemainingSeeds(true)
                        .direction(GameConfig.SteppingDirection.COUNTER_CLOCKWISE)
                        .emptyCapture(false)
                        .houses(6)
                        .seeds(6)
                        .pieRuleActive(false)
                        .build()
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
}
