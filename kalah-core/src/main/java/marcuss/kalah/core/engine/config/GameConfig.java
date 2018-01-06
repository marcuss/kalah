package marcuss.kalah.core.engine.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameConfig {

    private int houses;

    private int seeds;

    public enum GameDirection {COUNTER_CLOCKWISE, CLOCKWISE}

    private GameDirection direction;

    private Boolean emptyCapture;

    private Boolean countRemainingSeeds;

    private Boolean pieRuleActive;

    public static GameConfig DEFAULT = GameConfig.builder()
            .houses(6)
            .seeds(3)
            .direction(GameDirection.COUNTER_CLOCKWISE)
            .emptyCapture(false)
            .countRemainingSeeds(true)
            .pieRuleActive(false)
            .build();
}
