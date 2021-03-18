package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

public class PawnMoveStrategy extends LimitedMoveStrategy {

    private static final int PAWN_INITIAL_DISTANCE_LIMIT = 2;
    private static final int PAWN_DISTANCE_LIMIT = 1;
    private static final int PAWN_WHITE_INITIAL_POSITION = 2;
    private static final int PAWN_BLACK_INITIAL_POSITION = 7;

    @Override
    void checkValidMove(Position source, Position target, Board board) {
        checkPositionsOnBoard(source, target);
        checkDirection(source, target, board);
        checkIsNotSameTeam(source, target, board);
        checkMoveType(source, target, board);
        checkValidDistance(source, target);
    }

    private void checkDirection(Position source, Position target, Board board) {
        Piece piece = board.checkPieceAtPosition(source);
        if (!piece.isMovingForward(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    public void checkMoveType(Position source, Position target, Board board) {
        if (isVoid(target, board) && isDiagonalMove(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }

        if (!isVoid(target, board) && isLineMove(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }

    private boolean isVoid(Position target, Board board) {
        Piece targetPiece = board.checkPieceAtPosition(target);
        return targetPiece.equals(VOID_PIECE);
    }

    private void checkValidDistance(Position source, Position target) {
        int sourcePositionYAxis = source.getYPosition().getValue(); //TODO getter 줄이기

        if (sourcePositionYAxis == PAWN_WHITE_INITIAL_POSITION ||
            sourcePositionYAxis == PAWN_BLACK_INITIAL_POSITION) {
            checkInitialMoveDistance(source, target);
            return;
        }

        checkGeneralMoveDistance(source, target);
    }

    private void checkInitialMoveDistance(Position source, Position target) {
        if (Math.abs(source.computeVerticalDistance(target)) > PAWN_INITIAL_DISTANCE_LIMIT) {
            throw new InvalidMoveException(Piece.OVER_DISTANCE_MESSAGE);
        }
    }

    private void checkGeneralMoveDistance(Position source, Position target) {
        if (Math.abs(source.computeHorizontalDistance(target)) > PAWN_DISTANCE_LIMIT ||
            Math.abs(source.computeVerticalDistance(target)) > PAWN_DISTANCE_LIMIT) {
            throw new InvalidMoveException(Piece.OVER_DISTANCE_MESSAGE);
        }
    }
}
