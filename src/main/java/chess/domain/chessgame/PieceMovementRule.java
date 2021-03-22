package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Row;
import chess.domain.board.Team;
import chess.domain.piece.MoveVector;
import chess.domain.piece.Piece;

public class PieceMovementRule {

    public static final int FIRST_NEXT_MOVE_COUNT = 2;
    private static final int FIRST_MOVING_PAWN_RANGE = 2;

    private final Board board;

    public PieceMovementRule(Board board) {
        this.board = board;
    }

    public boolean canMove(Point source, Point destination, Team currentTeam) {

        return isValidSourceAndDestination(source, destination, currentTeam)
            && board.hasMovableVector(source, destination)
            && canMoveWithMoveVector(source, destination,
            board.movableVector(source, destination));
    }

    private boolean isValidSourceAndDestination(
        Point source, Point destination, Team currentTeam) {
        return board.isSameTeamAt(source, currentTeam)
            && board.isNotSameTeamAt(destination, currentTeam);

    }

    private boolean canMoveWithMoveVector(Point source, Point destination, MoveVector moveVector) {
        return isValidPath(source, destination, moveVector)
            && isNotPawnOrValidPawnMove(source, destination, moveVector);
    }

    private boolean isValidPath(Point source, Point destination, MoveVector moveVector) {
        int nextMoveCount = FIRST_NEXT_MOVE_COUNT;
        boolean success = true;

        for (Point now = source.movedPoint(moveVector); isNotArrived(destination, now) && success;
            now = now.movedPoint(moveVector)) {
            success = isWithinMovementRange(source, nextMoveCount, moveVector)
                && board.isSameTeamAt(now, Team.NONE);
            nextMoveCount++;
        }
        return success;
    }

    private boolean isNotArrived(Point destination, Point now) {
        return !now.equals(destination);
    }

    private boolean isWithinMovementRange(Point source, int moveCount, MoveVector moveVector) {
        int range = board.movementRange(source);
        if (isFirstStraightMovingPawn(source, moveVector)) {
            range = FIRST_MOVING_PAWN_RANGE;
        }
        return moveCount <= range;
    }

    private boolean isFirstStraightMovingPawn(Point source, MoveVector moveVector) {
        return board.isSamePieceTypeAt(source, Piece.PAWN)
            && moveVector.isPawnStraight()
            && (source.isRow(Row.TWO) || source.isRow(Row.SEVEN));
    }

    private boolean isNotPawnOrValidPawnMove(Point source, Point destination,
        MoveVector moveVector) {
        return board.isNotSamePieceTypeAt(source, Piece.PAWN) ||
            (isNotOrValidPawnStraightMove(destination, moveVector)
                && isNotOrValidPawnDiagonalMove(source, destination, moveVector));
    }

    private boolean isNotOrValidPawnStraightMove(Point destination, MoveVector moveVector) {
        return !moveVector.isPawnStraight() || board.isSameTeamAt(destination, Team.NONE);
    }

    private boolean isNotOrValidPawnDiagonalMove(Point source, Point destination,
        MoveVector moveVector) {
        return !moveVector.isDiagonalVector() || board.isNotSameTeam(source, destination);
    }
}
