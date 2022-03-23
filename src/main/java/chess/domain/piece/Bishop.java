package chess.domain.piece;

import static chess.domain.direction.Direction.*;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Bishop extends Piece {

    private static final String BISHOP_NAME  = "B";
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Bishop(Color color) {
        super(color, BISHOP_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public boolean isSingleMove() {
        return false;
    }
}
