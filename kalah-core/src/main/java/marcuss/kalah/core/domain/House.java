package marcuss.kalah.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class House extends Element{
    Integer seeds; //This is duplicate due lombok compatibility.
    Move.Turn owner;
    Integer pos;
}
