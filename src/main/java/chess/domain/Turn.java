package chess.domain;

public enum Turn {
    WHITE,
    BLACK;

    public Turn changeTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
