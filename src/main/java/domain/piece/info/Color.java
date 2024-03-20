package domain.piece.info;

public enum Color {
    BLACK(Direction.DOWN),
    WHITE(Direction.UP),
    NONE(Direction.UP);

    private final Direction direction;

    Color(final Direction direction) {
        this.direction = direction;
    }
}
