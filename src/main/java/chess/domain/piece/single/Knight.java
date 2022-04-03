package chess.domain.piece.single;

import static chess.domain.direction.Direction.DOWN_DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.direction.Direction.DOWN_LEFT_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_LEFT_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_UP_LEFT;
import static chess.domain.direction.Direction.UP_UP_RIGHT;

import chess.domain.Color;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Knight extends SingleMovePiece {

    private static final String KNIGHT_INITIAL_NAME = "N";
    private static final String KNIGHT_NAME = "knight";
    private static final double KNIGHT_SCORE = 2.5;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT,
            UP_UP_LEFT, UP_LEFT_LEFT, DOWN_DOWN_LEFT, DOWN_LEFT_LEFT);

    public Knight() {
        super(MOVE_DIRECTIONS);
    }

    @Override
    public String convertedName(Color color) {
        return color.convertToCase(KNIGHT_INITIAL_NAME);
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
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
        return KNIGHT_NAME;
    }
}
