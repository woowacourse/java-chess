package chess.game;

import chess.domain.Team;
import java.util.Random;

public class RandomTurnStrategy implements TurnStrategy {
    private final Random random;

    public RandomTurnStrategy() {
        this.random = new Random();
    }

    @Override

    public Team create() {
        if (random.nextBoolean()) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
