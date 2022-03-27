package chess.domain.piece.single;

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

public final class King extends SingleMovePiece {

    private static final String KING_NAME = "K";
    private static final double KING_SCORE = 0;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    public King(Color color) {
        super(color, KING_NAME, MOVE_DIRECTIONS);
    }

    @Override
    public final double score() {
        return KING_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final boolean isKing() {
        return true;
    }
}
