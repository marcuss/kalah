package marcuss.kalah.core.engine.strategies;

public class CountRemainingScoreStrategy implements ScoringStrategy {
    //todo: improve singleton as necessary
    private static CountRemainingScoreStrategy instance = new CountRemainingScoreStrategy();

    public static CountRemainingScoreStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean countRemaining) {
        return countRemaining;
    }
}
