package chess.domain.piece;

public enum Team {

    BLACK, WHITE, EMPTY;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
