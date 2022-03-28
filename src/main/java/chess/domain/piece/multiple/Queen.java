package chess.domain.piece.multiple;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.Color;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Queen extends MultipleMovablePiece {

    private static final String QUEEN_NAME = "Q";
    private static final double QUEEN_SCORE = 9;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Queen(Color color) {
        super(color, QUEEN_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public final double score() {
        return QUEEN_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
