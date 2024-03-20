package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }

    public int forwardDirection() {
        return this.isWhite() ? 1 : -1;
    }
}
