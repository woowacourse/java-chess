package chess.domain.piece;

import chess.domain.board.Square;

public class Empty extends Piece {

    public Empty(final Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.EMPTY;
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        return false;
    }
}
