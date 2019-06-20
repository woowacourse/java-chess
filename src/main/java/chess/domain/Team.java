package chess.domain;

public enum Team {
    WHITE(1, Direction.NORTH), BLACK(6, Direction.SOUTH);

    private final int firstIndex;
    private final Direction direction;

    Team(int firstIndex, Direction direction) {
        this.firstIndex = firstIndex;
        this.direction = direction;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public Direction getDirection() {
        return direction;
    }
}
