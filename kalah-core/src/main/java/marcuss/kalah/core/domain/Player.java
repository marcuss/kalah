package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Player {

    private Store store;

    private List<Element> houses;
}
