package chess.domain.piece;

import chess.domain.board.Square;

public class Empty extends Piece {
    private static final Empty EMPTY = new Empty(Camp.NONE);

    private Empty(Camp camp) {
        super(camp);
    }

    public static Empty getInstanceOf(Camp camp) {
        return EMPTY;
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return false;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.EMPTY;
    }
}
