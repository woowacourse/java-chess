package chess.domain.piece;

import chess.domain.util.Direction;

public enum Team {
    WHITE(Direction.NORTH, 2),
    BLACK(Direction.SOUTH, 7),
    BLANK(Direction.NONE, 0);

    private final Direction pawnForwardDirection;
    private final int initialPawnRow;

    Team(Direction pawnForwardDirection, int initialPawnRow) {
        this.pawnForwardDirection = pawnForwardDirection;
        this.initialPawnRow = initialPawnRow;
    }

    public boolean isNotSame(Team team) {
        return this != team && team != BLANK;
    }

    public Direction getForwardDirection() {
        return pawnForwardDirection;
    }

    public int getInitialPawnRow() {
        return initialPawnRow;
    }
}
