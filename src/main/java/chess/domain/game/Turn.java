package chess.domain.game;

public enum Turn {

    WHITE,
    BLACK;

    public Turn changeTurn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
