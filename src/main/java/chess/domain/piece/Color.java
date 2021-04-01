package chess.domain.piece;

public enum Color {
    BLACK, WHITE, NO_COLOR;

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
}
