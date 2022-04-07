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

public final class Queen extends MultipleMovePiece {

    private static final String QUEEN_INITIAL_NAME = "Q";
    private static final String QUEEN_NAME = "queen";
    private static final double QUEEN_SCORE = 9;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Queen() {
        super(MOVE_DIRECTIONS);
    }

    @Override
    public String convertedName(Color color) {
        return color.convertToCase(QUEEN_INITIAL_NAME);
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public String name() {
        return QUEEN_NAME;
    }
}
