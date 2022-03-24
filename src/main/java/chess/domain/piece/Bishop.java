package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Direction;
import chess.domain.position.Square;

public final class Bishop extends Piece {
    private final static String BUG_MESSAGE_COLOR = "[BUG] 비숍은 색상을 가져야합니다.";
    private static final String BLACK_BISHOP = "♝";
    private static final String WHITE_BISHOP = "♗";
    private static final List<Direction> DIRECTIONS = new ArrayList<>();

    static {
        DIRECTIONS.add(new Direction(1, 1));
        DIRECTIONS.add(new Direction(1, -1));
        DIRECTIONS.add(new Direction(-1, -1));
        DIRECTIONS.add(new Direction(-1, 1));
    }

    Bishop(Color color) {
        super(color);
    }

    @Override
    public String getEmoji() {
        if(color==Color.NONE){
            throw new IllegalArgumentException(BUG_MESSAGE_COLOR);
        }

        if(color==Color.BLACK){
            return BLACK_BISHOP;
        }

        return WHITE_BISHOP;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        Direction direction = source.getGap(target);
        return direction.hasMultiple(DIRECTIONS);
    }
}
