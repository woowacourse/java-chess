package chess.board.piece;

public enum Team {
    BLACK(-1),
    WHITE(1),
    NOTHING(0);

    private final int direction;

    Team(final int direction) {
        this.direction = direction;
    }

    public boolean isSameDirection(int direction) {
        return this.direction * direction > 0;
    }
}