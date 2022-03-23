package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.MovingOrder;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(new Name("."), Color.NONE);
    }

    @Override
    public void checkValidMove(Board board, MovingOrder movingOrder) {

    }

    @Override
    protected boolean isEmpty() {
        return true;
    }
}
