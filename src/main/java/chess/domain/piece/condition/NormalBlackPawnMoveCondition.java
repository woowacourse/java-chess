package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class NormalBlackPawnMoveCondition extends MoveCondition{
    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                target.equals(new Position(piece.getRow() + 1, piece.getColumn())) &&
                isNotExistPieceOnPath(board, target) &&
                validateChessPieceOutOfBoard(board, target);
    }

    private boolean isNotExistPieceOnPath(Board board, Position target) {
        return board.getPieces().stream()
                .noneMatch(p -> p.isSamePosition(target));
    }
}
