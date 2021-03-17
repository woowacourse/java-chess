package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;

public class QueenMoveStrategy extends BasicMoveStrategy {

    @Override
    public void move(Position source, Position target, Board board) {
        checkValidMove(source, target, board);

        Piece originalPiece = board.checkPieceAtPosition(source);
        board.putPieceAtPosition(target, originalPiece);

        board.putPieceAtPosition(source, VOID_PIECE);
    }

    private void checkValidMove(Position source, Position target, Board board) {
        checkPositionsOnBoard(source, target);
        checkMoveType(source, target);
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        checkIsNotSameTeam(source, target, board);
        checkClearPath(source, target, moveDirection, board);
    }

    private void checkMoveType(Position source, Position target) {
        if (!isLineMove(source, target) &&
            !isDiagonalMove(source, target)) {
            throw new InvalidMoveException(Piece.UNABLE_MOVE_TYPE_MESSAGE);
        }
    }
}
