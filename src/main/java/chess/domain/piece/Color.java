package chess.domain.piece;

import chess.domain.board.RankCoordinate;

public enum Color {
    BLACK,
    WHITE,
    EMPTY;

    public static Color of(RankCoordinate rankCoordinate) {
        if (rankCoordinate.isWhiteRank()) {
            return Color.WHITE;
        }
        if (rankCoordinate.isBlackRank()) {
            return Color.BLACK;
        }
        return Color.EMPTY;
    }

    public boolean isOpposite(Color color) {
        return this.getReverseColor() == color;
    }

    public Color getReverseColor() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }

    public int getDirection() {
        if (this == BLACK) {
            return -1;
        }
        return 1;
    }
}
