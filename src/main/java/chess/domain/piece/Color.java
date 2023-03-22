package chess.domain.piece;

public enum Color {
    BLACK(-1),
    WHITE(1),
    NONE(0);

    private final int forwardDirection;

    Color(int forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public int colorForwardDirection(int amount) {
        return amount * forwardDirection;
    }
}
