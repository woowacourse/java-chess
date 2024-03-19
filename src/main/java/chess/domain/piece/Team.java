package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE;

    public boolean isWhite() {
        return this == WHITE;
    }
}
