package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class MoveChecker {

    private static final String NOT_MOVABLE_EXCEPTION_MESSAGE = "해당 Position으로 이동할 수 없습니다.";

    public void checkTargetEnemyOrEmpty(Piece fromPiece, Piece toPiece) {
        if (!toPiece.isEmpty() && fromPiece.isSameColor(toPiece)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    public void checkMovableDirection(Position fromPosition, Position toPosition, Piece fromPiece) {
        if (!fromPiece.canMove(fromPosition, toPosition)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    public void checkPawnMove(Piece fromPiece, Piece toPiece, Direction direction) {
        if (fromPiece.isSamePieceType(PieceType.PAWN)) {
            checkPawnStraightMove(toPiece, direction);
            checkEnemyInDiagonal(fromPiece, toPiece, direction);
        }
    }

    private void checkPawnStraightMove(Piece toPiece, Direction direction) {
        if (direction.isPawnStraigtDirection()) {
            checkEmpty(toPiece);
        }
    }

    public void checkEmpty(Piece piece) {
        if (!piece.isEmpty()) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }

    private void checkEnemyInDiagonal(Piece fromPiece, Piece toPiece, Direction direction) {
        if (direction.isPawnStraigtDirection()) {
            return;
        }
        if (toPiece.isEmpty() || fromPiece.isSameColor(toPiece)) {
            throw new IllegalStateException(NOT_MOVABLE_EXCEPTION_MESSAGE);
        }
    }
}
