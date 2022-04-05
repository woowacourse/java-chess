package chess.domain.piece.mulitiplemovepiece;

import java.util.List;

import chess.domain.piece.Color;
import chess.domain.position.UnitDirection;

public final class Bishop extends MovingMultipleSquarePiece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 비숍은 색상을 가져야합니다.";
    private static final String BLACK_BISHOP = "♗";
    private static final String WHITE_BISHOP = "♝";

    private static final List<UnitDirection> MOVABLE_UNIT_DIRECTIONS = List.of(
        UnitDirection.NORTH_EAST,
        UnitDirection.NORTH_WEST,
        UnitDirection.SOUTH_EAST,
        UnitDirection.SOUTH_WEST
    );

    public Bishop(Color color) {
        super(color, 3, "bishop", MOVABLE_UNIT_DIRECTIONS);
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_BISHOP;
        }

        return WHITE_BISHOP;
    }
}
