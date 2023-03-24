package chess.domain;

public enum Color {
    BLACK(1),
    WHITE(-1),
    EMPTY(0);
    private final int direction;

    Color(final int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
