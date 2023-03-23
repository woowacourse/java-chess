package chess.domain.piece;

public enum Team {

    BLACK(-1),
    WHITE(1);

    private final int direction;

    Team(final int pawnDirection) {
        this.direction = pawnDirection;
    }

    public int getDirection() {
        return direction;
    }

    public Team flip() {
        if (this == BLACK) {
            return WHITE;
        }

        return BLACK;
    }
}
