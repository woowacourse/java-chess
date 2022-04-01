package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class QueenPiece extends Piece {

    private static final String NAME = "Q";

    private final List<Direction> movableDirections;

    public QueenPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
                List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH,
                        Direction.NORTH_EAST, Direction.NORTH_WEST,
                        Direction.SOUTH_EAST, Direction.SOUTH_WEST));
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        return movableDirections.contains(direction);
    }

}
