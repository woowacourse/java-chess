package chess.domain.piece;

public enum Direction {
    UP(1),
    DOWN(-1),
    ;

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
