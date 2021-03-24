package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public abstract class MoveCondition {

    public abstract boolean isSatisfyBy(Board board, Piece source, Position target);

    protected boolean isNotChessPieceOutOfBoard(Board board, Position target) {
        if (target.getRow() < 0) {
            return false;
        }
        if (target.getRow() >= board.getRow()) {
            return false;
        }
        if (target.getColumn() < 0) {
            return false;
        }
        return target.getColumn() < board.getColumn();
    }

}
