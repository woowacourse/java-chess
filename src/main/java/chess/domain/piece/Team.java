package chess.domain.piece;

public enum Team {
    BLACK, WHITE, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }
}
