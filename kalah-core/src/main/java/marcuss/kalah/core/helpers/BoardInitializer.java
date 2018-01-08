package marcuss.kalah.core.helpers;

import marcuss.kalah.core.domain.*;

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
                .store1(Store.builder().owner(PLAYER1).seeds(0).build())
                .store2(Store.builder().owner(PLAYER2).seeds(0).build())
                .player1(
                        Player.builder()
                                .houses(initHouses(houses, seeds, PLAYER1))
                                .store(Store.builder().owner(PLAYER1).seeds(0).build())
                                .build()
                )
                .player2(
                        Player.builder()
                                .houses(initHouses(houses, seeds, PLAYER2))
                                .store(Store.builder().owner(PLAYER2).seeds(0).build())
                                .build()
                )
                .build();
    }

    private static List<Element> initHouses(int houses, int seeds, Turn owner) {
        List<Element> elements = IntStream.range(0, houses)
                .mapToObj(i -> House.builder().seeds(seeds).owner(owner).build())
                .collect(Collectors.toList());
        int i = 0;
        for (Element e : elements) {
            ((House) e).setPos(i++);
        }
        return elements;
    }

}
