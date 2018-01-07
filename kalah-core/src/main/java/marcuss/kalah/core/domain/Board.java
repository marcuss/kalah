package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Data
@Builder
public class Board implements Iterable<Board.Element>{

    public static abstract class Element{
       public abstract Integer getValue();
       public abstract void setValue(Integer value);
       public abstract Move.Turn getOwner();
    }

    @Data
    @Builder
    public static class House extends Element{
        Integer value; //This is duplicate due lombok compatibility.
        Move.Turn owner;
        Integer pos;
    }
    @Data
    @Builder
    public static class Store extends Element{
        Integer value;
        Move.Turn owner;
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
