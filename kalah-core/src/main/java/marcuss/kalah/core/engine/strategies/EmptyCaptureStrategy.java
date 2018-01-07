package marcuss.kalah.core.engine.strategies;

public class EmptyCaptureStrategy implements CaptureStrategy {

    //todo: improve singleton as necessary
    private static EmptyCaptureStrategy instance = new EmptyCaptureStrategy();

    public static EmptyCaptureStrategy getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean emptyCapture) {
        return emptyCapture;
    }
}
