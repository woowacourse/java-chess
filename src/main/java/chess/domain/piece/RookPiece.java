package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class RookPiece extends Piece {

    private final List<Direction> movableDirections;

    public RookPiece(final Color color) {
        super(color, Type.ROOK);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH));
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        final int columnDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        final Direction direction = Direction.of(columnDistance, rowDistance);

        return movableDirections.contains(direction);
    }

    @Override
    public boolean isJumpable() {
        return false;
    }
}
