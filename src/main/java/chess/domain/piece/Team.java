package chess.domain.piece;

public enum Team {
    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }
}
