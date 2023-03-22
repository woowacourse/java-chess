package chess.domain.game;

import chess.domain.piece.Side;

public enum Turn {

    WHITE,
    BLACK;

    public Turn change() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isTurnValid(final Side side) {
        if (side.isWhite()) {
            return this == WHITE;
        }
        if (side.isBlack()) {
            return this == BLACK;
        }
        throw new IllegalArgumentException("기물이 있는 위치를 선택해주세요.");
    }
}
