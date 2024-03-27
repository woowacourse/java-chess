package domain.piece;

import domain.position.Position;

public enum Color {

    WHITE(Direction.UP),
    BLACK(Direction.DOWN),
    NONE(Direction.STOP);

    private final Direction direction;

    Color(Direction direction) {
        this.direction = direction;
    }

    public boolean canMove(Position source, Position target) {
        int rankDirection = source.rankDirection(target);
        return direction.isForward(rankDirection);
    }

    public Color opposite() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}

