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

    public String convertName(String name) {
        if (isWhite()) {
            return name.toLowerCase();
        }

        if (isBlack()) {
            return name.toUpperCase();
        }

        return name;
    }

    private boolean isBlack() {
        return this == BLACK;
    }

    private boolean isWhite() {
        return this == WHITE;
    }
}
