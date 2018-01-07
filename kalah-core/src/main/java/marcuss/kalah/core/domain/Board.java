package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Data
@Builder
public class Board implements Iterable<Board.Element>{

    @Data
    @Builder
    public static class Element{
        Integer value;
    }

    private Element store1;

    private Element store2;

    private List<Element> houses1;

    private List<Element> houses2;

    private Iterator boardIterator;

    @Override
    public Iterator iterator() {
        return boardIterator;
    }

    @Override
    public void forEach(Consumer action) {

    }
}
