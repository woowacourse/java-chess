package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Direction;

public final class Rook extends Piece {
    private static final String BUG_MESSAGE_COLOR = "[BUG] 룩은 색상을 가져야합니다.";
    private static final String BLACK_ROOK = "♜";
    private static final String WHITE_ROOK = "♖";
    private static final List<Direction> DIRECTIONS = new ArrayList<>();

    static {
        DIRECTIONS.add(new Direction(1, 0));
        DIRECTIONS.add(new Direction(0, 1));
        DIRECTIONS.add(new Direction(-1, 0));
        DIRECTIONS.add(new Direction(0, -1));
    }

    Rook(Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_ROOK;
        }

        return WHITE_ROOK;
    }

    @Override
    public boolean canMove(Direction direction) {
        return direction.hasMultiple(DIRECTIONS);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
