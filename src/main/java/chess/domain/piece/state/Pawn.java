package chess.domain.piece.state;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import java.util.List;

public final class Pawn extends Piece {

    private static final int SAME_FILE_DISTANCE = 0;
    private static final int TWO_FRONT_STEP_DISTANCE = 2;
    private static final int ONE_DIAGONAL_STEP_DISTANCE = 1;
    private static final int DIAGONAL_PIECE_INDEX = 0;

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
        return Math.abs(fileDistance) == ONE_DIAGONAL_STEP_DISTANCE && rankDistance == this.team.getDirection();
    }

    private boolean checkMoveOneStep(final int fileDistance, final int rankDistance) {
        return fileDistance == SAME_FILE_DISTANCE && rankDistance == this.team.getDirection();
    }

    private boolean checkMoveFirst(final int fileDistance, final int rankDistance) {
        return fileDistance == SAME_FILE_DISTANCE
                && rankDistance == TWO_FRONT_STEP_DISTANCE * this.team.getDirection() && !isMoved;
    }

    @Override
    public void validateRoute(final List<PieceState> pieceRoute) {
        if (isEnemyOnDiagonal) {
            validateMoveToDiagonal(pieceRoute.get(DIAGONAL_PIECE_INDEX));
            isMoved = true;
            return;
        }

        checkSquaresEmpty(pieceRoute);
        isMoved = true;
    }

    private void validateMoveToDiagonal(final PieceState square) {
        if (square.isSameTeam(this) || square.isEmpty()) {
            throwCanNotMoveException();
        }
    }
}
