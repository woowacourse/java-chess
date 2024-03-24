package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public Color convertTurn() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        if (this.equals(WHITE)) {
            return BLACK;
        }
        throw new UnsupportedOperationException("적절하지 않은 턴 전환입니다.");
    }
}
