package marcuss.kalah.core.engine.strategies;

import marcuss.kalah.core.domain.Board;
import marcuss.kalah.core.domain.Element;
import marcuss.kalah.core.domain.Move;
import marcuss.kalah.core.engine.config.GameConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static marcuss.kalah.core.engine.config.GameConfig.SteppingDirection.COUNTER_CLOCKWISE;


public class CounterClockWiseStepping implements SteppingDirectionStrategy {
    //todo: improve as necessary
    private static CounterClockWiseStepping instance = new CounterClockWiseStepping();

    public boolean appliesTo(GameConfig.SteppingDirection direction) {
        return direction.equals(COUNTER_CLOCKWISE);
    }

    @Override
    public Iterator<Element> getIterator(Move current, int house) {
        return new CounterClockWiseIterator(current, house);
    }

    // todo: Find a way to force all the implementors to be singletons (best with out spring)
    public static SteppingDirectionStrategy getInstance() {
        return instance;
    }


    class CounterClockWiseIterator implements Iterator<Element> {

        private final Move current;
        private final int house;
        private int currentPos;
        private List<Element> internalState = new ArrayList<>();

        public CounterClockWiseIterator(Move current, int house) {
            this.current = current;
            this.house = house;
            this.currentPos = 0;
            buildInternalState(current, house);
        }

        private void buildInternalState(Move current, int house) {
            Board board = current.getCurrentBoard();
            List<Element> player1Houses = board.getPlayer1().getHouses();
            List<Element> player2Houses = board.getPlayer2().getHouses();
            final int size = player2Houses.size();

            switch (current.getTurn()) {
                case PLAYER1:
                    for (int i = house; i >= 0; i--) {
                        internalState.add(player1Houses.get(i));
                    }
                    internalState.add(board.getPlayer1().getStore());
                    internalState.addAll(player2Houses);
                    for (int i = size - 1; i > house; i--) {
                        internalState.add(player1Houses.get(i));
                    }
                    break;

                case PLAYER2:

                    for (int i = house; i <= size - 1; i++) {
                        internalState.add(player2Houses.get(i));
                    }
                    internalState.add(board.getPlayer2().getStore());
                    for (int i = size - 1; i >= 0; i--) {
                        internalState.add(player1Houses.get(i));
                    }
                    for (int i = 0; i < house; i++) {
                        internalState.add(player2Houses.get(i));
                    }
                    break;

            }
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Element next() {
            // go round
            if (currentPos >= internalState.size()) currentPos = 0;
            return internalState.get(currentPos++);
        }
    }
}
