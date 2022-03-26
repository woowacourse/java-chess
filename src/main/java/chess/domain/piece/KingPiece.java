package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class KingPiece extends Piece {

    private static final String NAME = "K";

    private final List<Direction> movableDirections;

    public KingPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = new ArrayList<>(
            List.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH,
                Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST,
                Direction.SOUTH_WEST));
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        return movableDirections.contains(direction) &&
            Math.abs(fileDistance) < 2 && Math.abs(rankDistance) < 2;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
