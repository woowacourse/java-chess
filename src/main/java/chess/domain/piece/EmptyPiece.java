package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class EmptyPiece extends Piece {
    public EmptyPiece() {
        super(new Name("."), Color.NONE);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {

    }

    @Override
    protected boolean isEmpty() {
        return true;
    }
}
