package chess.domain.piece;

public enum Color {
    WHITE, BLACK, NOTHING;

    public boolean isSameAs(Color color) {
        return this.equals(color);
    }
}
