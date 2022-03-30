package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Direction;

public final class Queen extends MovingMultipleUnitPiece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 퀸은 색상을 가져야합니다.";
    private static final String WHITE_QUEEN = "♛";
    private static final String BLACK_QUEEN = "♕";
    private static final List<Direction> MOVABLE_DIRECTIONS = new ArrayList<>();

    static {
        MOVABLE_DIRECTIONS.add(new Direction(0, 1));
        MOVABLE_DIRECTIONS.add(new Direction(0, -1));
        MOVABLE_DIRECTIONS.add(new Direction(1, 0));
        MOVABLE_DIRECTIONS.add(new Direction(1, -1));
        MOVABLE_DIRECTIONS.add(new Direction(1, 1));
        MOVABLE_DIRECTIONS.add(new Direction(-1, 0));
        MOVABLE_DIRECTIONS.add(new Direction(-1, -1));
        MOVABLE_DIRECTIONS.add(new Direction(-1, 1));
    }

    Queen(Color color) {
        super(color, 9, MOVABLE_DIRECTIONS);
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_QUEEN;
        }

        return WHITE_QUEEN;
    }
}
