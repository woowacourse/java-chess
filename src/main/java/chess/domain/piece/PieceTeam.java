package chess.domain.piece;

public enum PieceTeam {
    WHITE(1),
    BLACK(-1),
    EMPTY(0);

    public final int movingDirection;

    PieceTeam(int movingDirection) {
        this.movingDirection = movingDirection;
    }

    public int direction() {
        return movingDirection;
    }
}
