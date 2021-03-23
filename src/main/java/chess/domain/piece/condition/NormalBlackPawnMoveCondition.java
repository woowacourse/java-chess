package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class NormalBlackPawnMoveCondition extends MoveCondition {

    @Override
    public boolean isSatisfyBy(final Board board, final Piece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isMovablePath(piece, target) &&
                isNotExistPieceOnPath(board, target) &&
                isNotChessPieceOutOfBoard(board, target);
    }

    private boolean isMovablePath(final Piece piece, final Position target) {
        return target.equals(new Position(piece.getRow() + 1, piece.getColumn()));
    }

    private boolean isNotExistPieceOnPath(Board board, Position target) {
        return board.isNoneMatchByFilteredPieces(piece -> true,
                piece -> piece.isSamePosition(target));
    }

}
