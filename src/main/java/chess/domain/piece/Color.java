package chess.domain.piece;

public enum Color {

    WHITE,
    BLACK;

    public boolean isSameColor(Color color) {
        return this == color;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public Color nextTurn() {
        if (this.isWhite()) {
            return BLACK;
        }
        return WHITE;
    }

}


