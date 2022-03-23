package chess.domain.piece;

import static chess.domain.direction.Direction.*;

import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Queen(Color color) {
        super(color, QUEEN_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public boolean isSingleMove() {
        return false;
    }
}
