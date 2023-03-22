package chess.game;

import chess.game.state.BlackTurnState;
import chess.game.state.RunningState;
import chess.game.state.WhiteTurnState;
import java.util.Random;

public class RandomTurnStrategy implements TurnStrategy {
    private final Random random;

    public RandomTurnStrategy() {
        this.random = new Random();
    }

    @Override

    public RunningState create() {
        if (random.nextBoolean()) {
            return WhiteTurnState.STATE;
        }
        return BlackTurnState.STATE;
    }
}
