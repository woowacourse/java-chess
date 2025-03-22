package chess;

public enum Turn {
    WHITE_TURN,
    BLACK_TURN;

    public static Turn getStartingTurn() {
        return WHITE_TURN;
    }

    public Turn changeTurn() {
        if (this == WHITE_TURN) {
            return BLACK_TURN;
        }
        return WHITE_TURN;
    }
}
