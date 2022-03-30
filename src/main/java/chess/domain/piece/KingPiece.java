package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class KingPiece extends Piece {

    private static final String NAME = "K";
    private static final int LIMIT_DISTANCE = 2;

    private final List<Direction> movableDirections;

    public KingPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH,
                        Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST,
                        Direction.SOUTH_WEST));
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        final int columnDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        final Direction direction = Direction.of(columnDistance, rowDistance);

        return movableDirections.contains(direction) &&
                Math.abs(columnDistance) < LIMIT_DISTANCE && Math.abs(rowDistance) < LIMIT_DISTANCE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
