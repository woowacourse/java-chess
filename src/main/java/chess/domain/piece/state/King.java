package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;

public final class King extends Piece {

    private static final int MIN_MOVE_DISTANCE = 1;

    public King(final Team team) {
        super(team);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        validatePossibleDestination(from, to);

        if (from.isPositiveDiagonal(to)) {
            return positiveDiagonalRoute(from, to);
        }

        if (from.isSameFile(to)) {
            return verticalRoute(from, to);
        }

        if (from.isNegativeDiagonal(to)) {
            return negativeDiagonalRoute(from, to);
        }
        return horizontalRoute(from, to);
    }

    private void validatePossibleDestination(final Coordinate from, final Coordinate to) {
        final int fileDistance = from.calculateFileDistance(to);
        final int rankDistance = from.calculateRankDistance(to);

        if (isCanNotMoveDirection(fileDistance, rankDistance)) {
            throwCanNotMoveException();
        }
    }

    private boolean isCanNotMoveDirection(final int fileDistance, final int rankDistance) {
        return fileDistance > MIN_MOVE_DISTANCE || rankDistance > MIN_MOVE_DISTANCE;
    }
}
