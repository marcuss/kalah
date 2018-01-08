package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Player implements Serializable{

    private Store store;

    private List<Element> houses;

    private int score;
}
