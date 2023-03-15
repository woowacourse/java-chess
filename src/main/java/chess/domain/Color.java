package chess.domain;

public enum Color {
    BLACK(-1),
    WHITE(1),
    NONE(0);

    private final int direction;

    Color(int direction) {
        this.direction = direction;
    }

    public int colorDirection(int amount) {
        return amount * direction;
    }
}
