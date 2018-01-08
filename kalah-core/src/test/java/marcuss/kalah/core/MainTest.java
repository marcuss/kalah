package marcuss.kalah.core;

import marcuss.kalah.core.domain.Board.House;
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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static marcuss.kalah.core.domain.Move.State.STARTED;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER1;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER2;
import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(
                PLAYER1,
                ((House) game.getBoard().getHouses1().get(0)).getOwner()
                );

        assertEquals(
                PLAYER2,
                ((House) game.getBoard().getHouses2().get(0)).getOwner()
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
    public void testRoundMoveAndCapture() {
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
        assertEquals(
                new Integer(0),
                game.getBoard().getHouses2().get(5).getValue()
        );
        assertEquals(
                new Integer(1),
                game.getBoard().getStore2().getValue()
        );

        game.move(3); // I t should land in the empty 0 house and capture 3 Player2 seeds.

        assertEquals(
                new Integer(6), //1 from the first move, 1 landing seed, and 5 in the rival opposite house.
                game.getBoard().getStore1().getValue()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 0, 1",
            "3, 4, 1, 1",
            "3, 4, 2, 1",
            "3, 4, 3, 0",
            "8, 3, 0, 2",
            "8, 3, 1, 1",
            "9, 3, 1, 2",
            "10, 3, 2, 2"
    })
    public void testExtremeCasesPlayer1(int configSeeds, int configHouses, int moveHouse, int expectedStore) {
        GameConfig config = GameConfig.DEFAULT;
        config.setHouses(configHouses);
        config.setSeeds(configSeeds);
        Game game = Game.startGame(config);
        game.move(moveHouse);

        assertEquals(
                new Integer(expectedStore),
                game.getBoard().getStore1().getValue()
        );
    }
    @ParameterizedTest
    @CsvSource({
            "3, 3, 4, 0, 0",
            "3, 3, 4, 1, 1",
            "3, 3, 4, 2, 1",
            "3, 3, 4, 3, 1",
            "2, 8, 3, 0, 1",
            "2, 8, 3, 1, 2",
            "2, 8, 3, 2, 2"
    })
    public void testExtremeCasesPlayer2(int emptyPlayer1House, int configSeeds, int configHouses, int moveHouse, int expectedStore) {
        GameConfig config = GameConfig.DEFAULT;
        config.setHouses(configHouses);
        config.setSeeds(configSeeds);
        Game game = Game.startGame(config);
        game.move(emptyPlayer1House);
        assertEquals(Move.Turn.PLAYER2, game.getCurrentMove().getTurn());
        game.move(moveHouse);

        assertEquals(
                new Integer(expectedStore),
                game.getBoard().getStore2().getValue()
        );
    }
}
