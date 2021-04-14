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

    public static Color of(char name) {
        if (Character.isLowerCase(name)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
