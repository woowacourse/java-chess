package chess.domain.piece;

public enum Team {

    BLACK(-1),
    WHITE(1);

    private final int pawnDirection;

    Team(final int pawnDirection) {
        this.pawnDirection = pawnDirection;
    }

    public Team getNextTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public int getPawnDirection() {
        return pawnDirection;
    }
}
