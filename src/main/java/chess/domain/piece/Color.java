package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Row;

public enum Color {

    BLACK(Row.SEVEN), WHITE(Row.TWO);

    private final Row rowOfStartPawn;

    Color(Row rowOfStartPawn) {
        this.rowOfStartPawn = rowOfStartPawn;
    }

    public boolean isForward(Position from, Position to) {
        if (this == WHITE) {
            return from.isUpward(to);
        }
        return from.isDownward(to);
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isStartPawnPosition(Position position) {
        return position.isSameRow(rowOfStartPawn);
    }
}
