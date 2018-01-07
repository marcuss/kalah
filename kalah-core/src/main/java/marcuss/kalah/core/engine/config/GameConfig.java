package marcuss.kalah.core.engine.config;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameConfig {

    private int houses;

    private int seeds;

    public enum SteppingDirection {COUNTER_CLOCKWISE, CLOCKWISE}

    private SteppingDirection direction;

    private Boolean emptyCapture;

    private Boolean countRemainingSeeds;

    private Boolean pieRuleActive;

    public static GameConfig DEFAULT = GameConfig.builder()
            .houses(6)
            .seeds(3)
            .direction(SteppingDirection.COUNTER_CLOCKWISE)
            .emptyCapture(false)
            .countRemainingSeeds(true)
            .pieRuleActive(false)
            .build();
}
