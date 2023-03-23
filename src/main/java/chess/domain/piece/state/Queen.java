package chess.domain.piece.state;

import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;

public final class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public List<SquareCoordinate> findRoute(final SquareCoordinate from, final SquareCoordinate to) {
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

    private void validatePossibleDestination(final SquareCoordinate from, final SquareCoordinate to) {
        if (!(from.isSameFile(to) || from.isSameRank(to) || from.isPositiveDiagonal(to) || from.isNegativeDiagonal(
                to))) {
            throwCanNotMoveException();
        }
    }
}
