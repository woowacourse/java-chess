package chess.domain.piece;

import chess.domain.board.Square;

public class Empty extends Piece {

    public Empty(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.EMPTY;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return false;
    }
}
