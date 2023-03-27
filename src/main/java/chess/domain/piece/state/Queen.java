package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.List;

public final class Queen extends Piece {

    public Queen(final Team team) {
        super(team, PieceType.QUEEN);
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
        if (isCanNotMoveDirection(from, to)) {
            throwCanNotMoveException();
        }
    }

    private boolean isCanNotMoveDirection(final Coordinate from, final Coordinate to) {
        return (from.isNotOrthogonal(to) && from.isNotDiagonal(to));
    }
}
