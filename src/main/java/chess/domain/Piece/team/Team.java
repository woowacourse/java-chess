package chess.domain.Piece.team;

import chess.domain.position.Direction;

public enum Team {
    WHITE(Direction.NORTH),
    BLACK(Direction.SOUTH),
    NOT_ASSIGNED(Direction.STAY);

    private Direction forwardDirection;

    Team(Direction forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public boolean isNotSame(Team team) {
        return this != team;
    }

    public Direction getForwardDirection() {
        return forwardDirection;
    }

    public boolean isSame(Team team) {
        return this == team;
    }
}
