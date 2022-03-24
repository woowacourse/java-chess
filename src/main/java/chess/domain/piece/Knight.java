package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Direction;

public final class Knight extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 나이트는 색상을 가져야합니다.";
    private static final String BLACK_KNIGHT = "♞";
    private static final String WHITE_KNIGHT = "♘";
    private static final List<Direction> DIRECTIONS = new ArrayList<>();

    static {
        DIRECTIONS.add(new Direction(1, 2));
        DIRECTIONS.add(new Direction(1, -2));
        DIRECTIONS.add(new Direction(-1, 2));
        DIRECTIONS.add(new Direction(-1, -2));
        DIRECTIONS.add(new Direction(2, 1));
        DIRECTIONS.add(new Direction(2, -1));
        DIRECTIONS.add(new Direction(-2, -1));
        DIRECTIONS.add(new Direction(-2, 1));
    }

    Knight(Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_KNIGHT;
        }

        return WHITE_KNIGHT;
    }

    @Override
    public boolean canMove(Direction direction) {
        return direction.hasSame(DIRECTIONS);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
