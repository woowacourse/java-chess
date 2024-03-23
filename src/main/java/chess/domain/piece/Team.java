package chess.domain.piece;

import static chess.domain.position.Direction.DOWN;
import static chess.domain.position.Direction.UP;

import chess.domain.position.Direction;
import chess.domain.position.Rank;

public enum Team {
    WHITE(UP, Rank.TWO),
    BLACK(DOWN, Rank.SIX);

    private final Direction direction;
    private final Rank initialPawnRank;

    Team(Direction direction, Rank initialPawnRank) {
        this.direction = direction;
        this.initialPawnRank = initialPawnRank;
    }

    public Direction getDirection() {
        return direction;
    }

    public Rank getInitialPawnRank() {
        return initialPawnRank;
    }
}
