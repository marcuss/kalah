package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.Board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static marcuss.kalah.core.domain.Board.*;
import static marcuss.kalah.core.domain.Move.Turn;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER1;
import static marcuss.kalah.core.domain.Move.Turn.PLAYER2;

public class BoardInitializer {

    public static Board initKalah(int houses, final int seeds) {
        return builder()
                .houses1(initHouses(houses, seeds, PLAYER1))
                .houses2(initHouses(houses, seeds, PLAYER2))
                .store1(Store.builder().owner(PLAYER1).value(0).build())
                .store2(Store.builder().owner(PLAYER2).value(0).build())
                .build();
    }

    private static List<Element> initHouses(int houses, int seeds, Turn owner) {
        List<Element> elements = IntStream.range(0, houses)
                .mapToObj(i -> House.builder().value(seeds).owner(owner).build())
                .collect(Collectors.toList());
        int i = 0;
        for (Element e : elements) {
            ((House) e).setPos(i++);
        }
        return elements;
    }

}
