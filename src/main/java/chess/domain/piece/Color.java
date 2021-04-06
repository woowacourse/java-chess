package chess.domain.piece;

public enum Color {
    BLACK, WHITE, NONE;

    public boolean isWhite() {
        return this.equals(WHITE);
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public Color getOppositeColor() {
        if (this.isBlack()) {
            return Color.WHITE;
        }
        if (this.isWhite()) {
            return Color.BLACK;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
