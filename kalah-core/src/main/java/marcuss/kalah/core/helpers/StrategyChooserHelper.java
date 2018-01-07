package marcuss.kalah.core.helpers;

import marcuss.kalah.core.engine.GameEngine;
import marcuss.kalah.core.engine.config.GameConfig;
import marcuss.kalah.core.engine.strategies.CaptureStrategy;
import marcuss.kalah.core.engine.strategies.ScoringStrategy;
import marcuss.kalah.core.engine.strategies.SteppingDirectionStrategy;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class StrategyChooserHelper {

    //TODO: This approach altho was to prevent coupling, ended up coupling whole lot more,
    //this must be change to an spring like approach @Component... @Autowire List<Strategies>
    //That would do basically the same, list all available strategies, and help choose the right one.
    //This same class can be used to retrieve all the strategies from the application context and keep the
    //changes in this class.
    public static void setSteppingDirectionStrategy(GameConfig.SteppingDirection direction, GameEngine engine) {
        Set<Class<? extends SteppingDirectionStrategy>> availableSteppingStrategies =
                new Reflections("marcuss.kalah.core.engine.strategies")
                        .getSubTypesOf(SteppingDirectionStrategy.class);

        availableSteppingStrategies.stream().forEach(
                clazz -> {
                    try {
                        Method getInstance =
                                clazz.getMethod("getInstance", null);
                        SteppingDirectionStrategy instance =
                                (SteppingDirectionStrategy) getInstance.invoke(null, null);

                        Method method = clazz.getMethod("appliesTo", GameConfig.SteppingDirection.class);
                        if ((boolean) method.invoke(instance, direction)) {
                            engine.setSteppingStrategy(instance);
                        }
                    } catch (Exception e) {

                    }
                }
        );
    }

    public static void setCaptureStrategy(boolean emptyCapture, GameEngine engine) {
        Set<Class<? extends CaptureStrategy>> availableCaptureStrategies =
                new Reflections("marcuss.kalah.core.engine.strategies")
                        .getSubTypesOf(CaptureStrategy.class);

        availableCaptureStrategies.stream().forEach(
                clazz -> {
                    try {
                        Method getInstance =
                                clazz.getMethod("getInstance", null);
                        CaptureStrategy instance =
                                (CaptureStrategy) getInstance.invoke(null, null);

                        Method method = clazz.getMethod("appliesTo", Boolean.class);
                        if ((boolean) method.invoke(instance, emptyCapture)) {
                            engine.setCaptureStrategy(instance);
                        }
                    } catch (Exception e) {

                    }
                }
        );
    }

    public static void setScoringStrategy(boolean emptyCapture, GameEngine engine) {
        Set<Class<? extends ScoringStrategy>> availableScoringStrategy =
                new Reflections("marcuss.kalah.core.engine.strategies")
                        .getSubTypesOf(ScoringStrategy.class);

        availableScoringStrategy.stream().forEach(
                clazz -> {
                    try {
                        Method getInstance =
                                clazz.getMethod("getInstance", null);
                        ScoringStrategy instance =
                                (ScoringStrategy) getInstance.invoke(null, null);

                        Method method = clazz.getMethod("appliesTo", Boolean.class);
                        if ((boolean) method.invoke(instance, emptyCapture)) {
                            engine.setScoringStrategy(instance);
                        }
                    } catch (Exception e) {

                    }
                }
        );
    }
}
