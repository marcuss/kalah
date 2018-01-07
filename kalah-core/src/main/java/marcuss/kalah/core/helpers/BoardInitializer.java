package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.Board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardInitializer {

    public static Board initKalah(int houses, final int seeds) {
        return Board.builder()
                .houses1(initHouses(houses, seeds))
                .houses2(initHouses(houses, seeds))
                .store1(Board.Element.builder().value(0).build())
                .store2(Board.Element.builder().value(0).build())
                .build();
    }

    private static List<Board.Element> initHouses(int houses, int seeds) {
        return IntStream.range(0, houses)
                .mapToObj(i -> Board.Element.builder().value(seeds).build())
                .collect(Collectors.toList());
    }

}
