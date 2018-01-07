package marcuss.kalah.core.engine.strategies;

public class DontCountRemainingScoreStrategy implements ScoringStrategy{
    //todo: improve singleton as necessary
    private static DontCountRemainingScoreStrategy instance = new DontCountRemainingScoreStrategy();

    public static DontCountRemainingScoreStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean countRemaining) {
        return !countRemaining;
    }
}
