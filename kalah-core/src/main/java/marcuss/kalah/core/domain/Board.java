package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Data
@Builder
public class Board implements Iterable<Integer>{

    private Integer store1;

    private Integer store2;

    private List<Integer> houses1;

    private List<Integer> houses2;

    private Iterator boardIterator;

    @Override
    public Iterator iterator() {
        return boardIterator;
    }

    @Override
    public void forEach(Consumer action) {

    }
}
