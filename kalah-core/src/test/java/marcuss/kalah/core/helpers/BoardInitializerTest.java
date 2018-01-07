package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Board.Element;
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
                board.getHouses1().stream().filter(h -> h.getValue() == seeds)
                        .count()
        );

        assertEquals(houses,
                board.getHouses2().stream().filter(h -> h.getValue() == seeds)
                        .count()
        );

        assertEquals(houses * 2 * seeds,
                board.getHouses2().stream()
                        .mapToInt(Element::getValue).sum()
                        +
                        board.getHouses2().stream()
                                .mapToInt(Element::getValue).sum()
        );

        assertEquals(
                board.getHouses1().size(),
                board.getHouses2().size()
        );

        assertEquals(
                new Integer(0),
                board.getStore1()
        );

        assertEquals(
                new Integer(0),
                board.getStore2()
        );
    }
}