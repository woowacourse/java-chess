package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.board.Direction;
import chess.board.Square;

public final class Pawn extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 폰은 색상을 가져야합니다.";
    private static final String BLACK_PAWN = "♟";
    private static final String WHITE_PAWN = "♙";
    private static final List<Direction> DIRECTIONS = new ArrayList<>();

    static {
        DIRECTIONS.add(new Direction(0, 1));
    }

    private boolean start;

    Pawn(Color color) {
        super(color);
        this.start = true;
    }

    @Override
    public String getEmoji() {
        if (color == Color.NONE) {
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if (color == Color.BLACK) {
            return BLACK_PAWN;
        }

        return WHITE_PAWN;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        Direction direction = source.getGap(target);
        if (start) {
            this.start = false;
            return canMoveAtStart(direction);
        }
        return direction.hasSame(DIRECTIONS);
    }

    private boolean canMoveAtStart(Direction direction) {
        List<Direction> startDirections = new ArrayList<>(DIRECTIONS);
        startDirections.add(new Direction(0, 2));
        return direction.hasSame(startDirections);
    }
}
