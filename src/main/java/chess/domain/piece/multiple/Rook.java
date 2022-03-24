package chess.domain.piece.multiple;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;

import chess.domain.Color;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public final class Rook extends MultiplePiece {

    private static final String ROOK_NAME = "R";
    private static final double ROOK_SCORE = 5;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color, ROOK_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public final double score() {
        return ROOK_SCORE;
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
