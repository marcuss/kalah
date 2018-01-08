package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;

@Data
@Builder
public class Board implements Iterable<Element> {

    private Player player1;

    private Player player2;

    private Iterator boardIterator;

    @Override
    public Iterator iterator() {
        return boardIterator;
    }

}
