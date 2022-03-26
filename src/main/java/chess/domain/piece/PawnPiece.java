package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class PawnPiece extends Piece {

    private static final String NAME = "P";

    private final List<Direction> movableDirections;

    public PawnPiece(final Color color) {
        super(color, NAME);
        this.movableDirections = decideMovableDirections(color);
    }

    private List<Direction> decideMovableDirections(final Color color) {
        if (color == Color.WHITE) {
            return new ArrayList<>(
                List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST));
        }
        return new ArrayList<>(
            List.of(Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST));
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        if (isInitialPositionAndMoveTwice(from, fileDistance, rankDistance)) {
            return true;
        }

        if ((direction == Direction.NORTH || direction == Direction.SOUTH) && !isEmptyTarget) {
            return false;
        }

        if (direction != Direction.NORTH && direction != Direction.SOUTH && isEmptyTarget) {
            return false;
        }

        return movableDirections.contains(direction) &&
            Math.abs(fileDistance) < 2 && Math.abs(rankDistance) < 2;
    }

    private boolean isInitialPositionAndMoveTwice(final Position from, final int fileDistance,
        final int rankDistance) {
        if (super.isSameColor(Color.BLACK) && from.isSameRank(Rank.SEVEN) && fileDistance == 0
            && rankDistance == -2) {
            return true;
        }
        return super.isSameColor(Color.WHITE) && from.isSameRank(Rank.TWO) && fileDistance == 0
            && rankDistance == 2;
    }

}
