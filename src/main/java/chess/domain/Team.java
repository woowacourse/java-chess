package chess.domain;

public enum Team {
    WHITE(1, Direction.NORTH), BLACK(6, Direction.SOUTH), BLANK(0, Direction.NOT_FIND);

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
