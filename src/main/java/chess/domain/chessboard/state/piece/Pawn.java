package chess.domain.chessboard.state.piece;

import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.domain.chessboard.state.Team;
import java.util.List;

public final class Pawn extends Piece {

    private boolean isMoved = false;
    private boolean isEnemyOnDiagonal = false;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public List<Coordinate> findRoute(final Coordinate from, final Coordinate to) {
        validatePossibleDestination(from, to);
        isEnemyOnDiagonal = false;

        if (from.isSameFile(to)) {
            return verticalRoute(from, to);
        }

        isEnemyOnDiagonal = true;
        if (from.isPositiveDiagonal(to)) {
            return positiveDiagonalRoute(from, to);
        }

        return negativeDiagonalRoute(from, to);
    }

    private void validatePossibleDestination(final Coordinate from, final Coordinate to) {
        final int fileDistance = from.calculateFileDistance(to);
        final int rankDistance = from.calculateRankDistance(to);

        final boolean isMoveDiagonal = checkMoveDiagonal(fileDistance, rankDistance);
        final boolean isMoveOneStep = checkMoveOneStep(fileDistance, rankDistance);
        final boolean isMoveFirst = checkMoveFirst(fileDistance, rankDistance);

        if (!isMoveDiagonal && !isMoveOneStep && !isMoveFirst) {
            throwCanNotMoveException();
        }
    }

    private boolean checkMoveDiagonal(final int fileDistance, final int rankDistance) {
        return Math.abs(fileDistance) == 1 && rankDistance == this.team.getPawnDirection();
    }

    private boolean checkMoveOneStep(final int fileDistance, final int rankDistance) {
        return fileDistance == 0 && rankDistance == this.team.getPawnDirection();
    }

    private boolean checkMoveFirst(final int fileDistance, final int rankDistance) {
        return fileDistance == 0 && rankDistance == 2 * this.team.getPawnDirection() && !isMoved;
    }

    @Override
    public void validateRoute(final List<Square> routeSquares) {
        if (isEnemyOnDiagonal) {
            validateMoveToDiagonal(routeSquares.get(0));
            isMoved = true;
            return;
        }

        checkSquaresEmpty(routeSquares);
        isMoved = true;
    }

    private void validateMoveToDiagonal(final Square square) {
        if (square.isSameTeam(this) || square.isEmpty()) {
            throwCanNotMoveException();
        }
    }
}
