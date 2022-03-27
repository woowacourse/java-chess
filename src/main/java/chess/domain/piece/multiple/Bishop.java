package chess.domain.piece.multiple;

import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.Color;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Bishop extends MultipleMovePiece {

    private static final String BISHOP_NAME = "B";
    private static final double BISHOP_SCORE = 3;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public Bishop(Color color) {
        super(color, BISHOP_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
