package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class KnightPiece extends Piece {

    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    private final List<Direction> movableDirections;

    public KnightPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.KNIGHT_EAST_LEFT, Direction.KNIGHT_EAST_RIGHT,
                        Direction.KNIGHT_WEST_LEFT, Direction.KNIGHT_WEST_RIGHT,
                        Direction.KNIGHT_NORTH_LEFT, Direction.KNIGHT_NORTH_RIGHT,
                        Direction.KNIGHT_SOUTH_LEFT,
                        Direction.KNIGHT_SOUTH_RIGHT));
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        final int columnDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        final Direction direction = Direction.of(columnDistance, rowDistance);

        return movableDirections.contains(direction);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isJumpable() {
        return true;
    }
}
