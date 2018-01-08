package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Player;

public class CountRemainingScoreStrategy implements ScoringStrategy {
    //todo: improve singleton as necessary
    private static CountRemainingScoreStrategy instance = new CountRemainingScoreStrategy();

    public static CountRemainingScoreStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean countRemaining) {
        return countRemaining;
    }

    @Override
    public void score(Player player) {
        player.setScore(
                player.getHouses().stream()
                        .mapToInt(h -> h.getSeeds().intValue())
                        .sum() + player.getStore().getSeeds()
        );
    }
}
