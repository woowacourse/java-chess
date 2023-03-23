package chess.domain.piece;

public enum Team {

    BLACK(-1),
    WHITE(1);

    private final int pawnDirection;

    Team(final int pawnDirection) {
        this.pawnDirection = pawnDirection;
    }

    public int getPawnDirection() {
        return pawnDirection;
    }
}
