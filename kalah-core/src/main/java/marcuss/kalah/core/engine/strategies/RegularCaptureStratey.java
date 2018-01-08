package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Board.Element;
import marcuss.kalah.core.domain.Board.House;
import marcuss.kalah.core.domain.Board.Store;
import marcuss.kalah.core.domain.Move;

public class RegularCaptureStratey implements CaptureStrategy {
    //todo: improve singleton as necessary
    private static RegularCaptureStratey instance = new RegularCaptureStratey();

    public static RegularCaptureStratey getInstance() {
        return instance;
    }

    public boolean appliesTo(Boolean emptyCapture) {
        return !emptyCapture;
    }

    @Override
    public void capture(Element element, Element oppositeElement, Element playerStore, Move.Turn player) {
        if (element instanceof Store) {
            return;
        }

        if (element instanceof House
                && element.getSeeds().equals(1)
                && player.equals((element).getOwner())
                && oppositeElement.getSeeds() > 0) {
            playerStore.setSeeds(playerStore.getSeeds() + oppositeElement.getSeeds() + element.getSeeds());
            element.setSeeds(0);
        }
    }
}
