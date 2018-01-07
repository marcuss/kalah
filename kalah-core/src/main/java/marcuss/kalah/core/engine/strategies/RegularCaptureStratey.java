package marcuss.kalah.core.engine.strategies;

public class RegularCaptureStratey implements CaptureStrategy{
    //todo: improve singleton as necessary
    private static RegularCaptureStratey instance = new RegularCaptureStratey();

    public static RegularCaptureStratey getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean emptyCapture) {
        return !emptyCapture;
    }
}
