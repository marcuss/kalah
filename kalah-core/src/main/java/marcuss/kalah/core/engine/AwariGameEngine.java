package marcuss.kalah.core.engine;

import marcuss.kalah.core.engine.strategies.CounterClockWiseStepping;
import marcuss.kalah.core.engine.strategies.RegularCaptureStratey;

public class AwariGameEngine extends GameEngine {

    public AwariGameEngine() {
        captureStrategy = new RegularCaptureStratey();
        steppingStrategy = new CounterClockWiseStepping();
    }
}
