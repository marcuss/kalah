package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Element;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by marcuss on 1/6/18.
 */
class BoardInitializerTest {

    @ParameterizedTest
    @CsvSource({"6, 4", "4, 2", "3,3", "0,0", "1,0"})
    void testInitKalah(int houses, int seeds) {
        Board board = BoardInitializer.initKalah(houses, seeds);

        assertEquals(houses,
                board.getPlayer1().getHouses().stream().filter(h -> h.getSeeds() == seeds)
                        .count()
        );

        assertEquals(houses,
                board.getPlayer2().getHouses().stream().filter(h -> h.getSeeds() == seeds)
                        .count()
        );

        assertEquals(houses * 2 * seeds,
                board.getPlayer2().getHouses().stream()
                        .mapToInt(Element::getSeeds).sum()
                        +
                        board.getPlayer2().getHouses().stream()
                                .mapToInt(Element::getSeeds).sum()
        );

        assertEquals(
                board.getPlayer1().getHouses().size(),
                board.getPlayer2().getHouses().size()
        );

        assertEquals(
                new Integer(0),
                board.getPlayer1().getStore().getSeeds()
        );

        assertEquals(
                new Integer(0),
                board.getPlayer2().getStore().getSeeds()
        );
    }
}