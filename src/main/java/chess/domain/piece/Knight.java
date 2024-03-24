package chess.domain.piece;

import static chess.domain.Direction.DOWN_DOWN_LEFT;
import static chess.domain.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.Direction.LEFT_LEFT_DOWN;
import static chess.domain.Direction.LEFT_LEFT_UP;
import static chess.domain.Direction.RIGHT_RIGHT_DOWN;
import static chess.domain.Direction.RIGHT_RIGHT_UP;
import static chess.domain.Direction.UP_UP_LEFT;
import static chess.domain.Direction.UP_UP_RIGHT;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class Knight extends Piece {
    private static final List<Direction> KNIGHT_DIRECTION;

    static {
        KNIGHT_DIRECTION = List.of(
                UP_UP_LEFT, UP_UP_RIGHT,
                DOWN_DOWN_LEFT, DOWN_DOWN_RIGHT,
                LEFT_LEFT_UP, LEFT_LEFT_DOWN,
                RIGHT_RIGHT_DOWN, RIGHT_RIGHT_UP
        );
    }

    private static final int MAX_MOVE_DISTANCE = 2;

    public Knight(final Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        int fileDiff = Math.abs(movement.getFileDifference());
        int rankDiff = Math.abs(movement.getRankDifference());
        Direction direction = Direction.of(fileDiff, rankDiff);

        return KNIGHT_DIRECTION.contains(direction) && movement.calculateMaxDistance() == MAX_MOVE_DISTANCE;
    }
}
