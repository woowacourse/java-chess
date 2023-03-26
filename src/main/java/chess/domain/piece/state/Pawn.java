package chess.domain.piece.state;

import chess.domain.chessboard.SquareCoordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.SquareState;
import chess.domain.piece.Team;

import java.util.List;

public final class Pawn extends Piece {

    public static final int WHITE_PAWN_DIRECTION = 1;
    public static final int BLACK_PAWN_DIRECTION = -1;
    private boolean isMoved = false;
    private boolean isEnemyOnDiagonal = false;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public List<SquareCoordinate> findRoute(final SquareCoordinate from, final SquareCoordinate to) {
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

    private void validatePossibleDestination(final SquareCoordinate from, final SquareCoordinate to) {
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
        return Math.abs(fileDistance) == 1 && rankDistance == getPawnDirection(this.team);
    }

    private int getPawnDirection(Team team){
        if(team == Team.WHITE){
            return WHITE_PAWN_DIRECTION;
        }
        if(team == Team.BLACK){
            return BLACK_PAWN_DIRECTION;
        }
        throw new IllegalArgumentException("잘못된 팀입니다.");
    }

    private boolean checkMoveOneStep(final int fileDistance, final int rankDistance) {
        return fileDistance == 0 && rankDistance == getPawnDirection(this.team);
    }

    private boolean checkMoveFirst(final int fileDistance, final int rankDistance) {
        return fileDistance == 0 && rankDistance == 2 * getPawnDirection(this.team) && !isMoved;
    }

    @Override
    public void validateRoute(final List<SquareState> routeSquares) {
        if (isEnemyOnDiagonal) {
            validateMoveToDiagonal(routeSquares.get(0));
            isMoved = true;
            return;
        }

        checkSquaresEmpty(routeSquares);
        isMoved = true;
    }

    private void validateMoveToDiagonal(final SquareState squareState) {
        if (squareState.isSameTeam(this) || squareState.isEmpty()) {
            throwCanNotMoveException();
        }
    }
}
