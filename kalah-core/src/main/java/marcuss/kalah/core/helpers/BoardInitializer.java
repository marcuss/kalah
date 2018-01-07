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
                .build();

    }

    private static List<Integer> initHouses(int houses, int seeds) {
        return IntStream.range(0, houses)
                .mapToObj(i -> new Integer(seeds))
                .collect(Collectors.toList());
    }

}
