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

    public int getDirection() {
        if (this == BLACK) {
            return -1;
        }
        return 1;
    }
}
