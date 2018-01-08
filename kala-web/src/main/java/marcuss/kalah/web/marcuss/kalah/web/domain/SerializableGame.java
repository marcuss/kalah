package marcuss.kalah.web.marcuss.kalah.web.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import marcuss.kalah.core.Game;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class SerializableGame implements Serializable {

    private int houses;
    private int seeds;
    private String turn;

    public void setFields(Game game) {
        houses = game.getConfig().getHouses();
        seeds = game.getConfig().getSeeds();
        turn = game.getCurrentMove().getTurn().toString();
    }
}
