package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Board {

    private int store1;

    private int store2;

    private List<Integer> houses1;

    private List<Integer> houses2;

}
