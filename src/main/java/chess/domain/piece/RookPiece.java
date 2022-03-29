package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class RookPiece extends Piece {

    private static final String NAME = "R";

    private final List<Direction> movableDirections;

    public RookPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH));
    }

    @Override
    public boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        return movableDirections.contains(direction);
    }
}
