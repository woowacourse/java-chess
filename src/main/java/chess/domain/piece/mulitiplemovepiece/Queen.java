package chess.domain.piece.mulitiplemovepiece;

import java.util.List;

import chess.domain.piece.Color;
import chess.domain.position.UnitDirection;

public final class Queen extends MovingMultipleSquarePiece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 퀸은 색상을 가져야합니다.";
    private static final String WHITE_QUEEN = "♛";
    private static final String BLACK_QUEEN = "♕";

    private static final List<UnitDirection> MOVABLE_UNIT_DIRECTIONS = List.of(
        UnitDirection.NORTH,
        UnitDirection.SOUTH,
        UnitDirection.EAST,
        UnitDirection.WEST,
        UnitDirection.NORTH_EAST,
        UnitDirection.NORTH_WEST,
        UnitDirection.SOUTH_EAST,
        UnitDirection.SOUTH_WEST
    );

    public Queen(Color color) {
        super(color, 9, "queen", MOVABLE_UNIT_DIRECTIONS);
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
