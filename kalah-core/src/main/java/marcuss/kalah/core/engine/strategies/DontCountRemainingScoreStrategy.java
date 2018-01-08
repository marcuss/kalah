package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Player;

public class DontCountRemainingScoreStrategy implements ScoringStrategy {
    //todo: improve singleton as necessary
    private static DontCountRemainingScoreStrategy instance = new DontCountRemainingScoreStrategy();

    public static DontCountRemainingScoreStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean countRemaining) {
        return !countRemaining;
    }

    @Override
    public int score(Player player) {
        return player.getStore().getSeeds();
    }
}
