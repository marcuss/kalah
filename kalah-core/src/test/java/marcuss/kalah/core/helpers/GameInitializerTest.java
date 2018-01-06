package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by marcuss on 1/6/18.
 */
class GameInitializerTest {

    @Test
    void testInitKalah6x4() {
        int houses = 6;
        int seeds = 4;
        Board board6x4 = GameInitializer.initKalah(houses, seeds);

        assertEquals(houses,
                board6x4.getHouses1().stream().filter(h -> h == seeds)
                        .count()
        );

        assertEquals(houses,
                board6x4.getHouses2().stream().filter(h -> h == seeds)
                        .count()
        );

        assertEquals(
                board6x4.getHouses1().size(),
                board6x4.getHouses2().size()
        );
    }

}