package domain;

public enum Turn {
    BLACK, WHITE;

    public Turn switchTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
