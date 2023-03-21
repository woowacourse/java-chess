package chess.domain;

public enum Turn {
    WHITE_TURN,
    BLACK_TURN;

    public Turn changeTurn() {
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }
}
