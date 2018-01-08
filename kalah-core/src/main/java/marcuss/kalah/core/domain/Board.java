package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
@Builder
public class Board implements Iterable<Element> {


    private Element store1;

    private Element store2;

    private List<Element> houses1;

    private List<Element> houses2;

    private Player player1;
    private Player player2;

    private Iterator boardIterator;

    @Override
    public Iterator iterator() {
        return boardIterator;
    }

}
