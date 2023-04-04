package domain;

import domain.piece.Piece;

public enum Turn {
    BLACK, WHITE;

    public Turn switchTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isTurnOf(Piece piece) {
        return (piece.isBlack() && this == BLACK) || (piece.isWhite() && this == WHITE);
    }
}
