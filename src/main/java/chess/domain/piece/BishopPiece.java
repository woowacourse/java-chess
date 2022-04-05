package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public final class BishopPiece extends Piece {

    private final List<Direction> movableDirections;

    public BishopPiece(final Color color) {
        super(color, Type.BISHOP);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST,
                        Direction.SOUTH_WEST));
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
