package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class RookPiece extends Piece {

    private static final String NAME = "R";
    private static final double SCORE = 5.0;

    private final List<Direction> movableDirections;

    public RookPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH));
    }

    @Override
    public boolean isRightMovement(final Position from,
                                   final Position to,
                                   final boolean isEmptyTarget) {
        final int columnDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        final Direction direction = Direction.of(columnDistance, rowDistance);

        return movableDirections.contains(direction);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
