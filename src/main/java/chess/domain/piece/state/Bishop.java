package chess.domain.piece.state;

import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;

public final class Bishop extends Piece {

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public List<SquareCoordinate> findRoute(final SquareCoordinate from, final SquareCoordinate to) {
        validatePossibleDestination(from, to);

        if (from.isPositiveDiagonal(to)) {
            return positiveDiagonalRoute(from, to);
        }
        return negativeDiagonalRoute(from, to);
    }

    private void validatePossibleDestination(final SquareCoordinate from, final SquareCoordinate to) {
        if (!(from.isPositiveDiagonal(to) || from.isNegativeDiagonal(to))) {
            throwCanNotMoveException();
        }
    }
}
