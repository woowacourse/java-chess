package domain.piece;

import domain.board.Color;
import domain.board.position.Vector;

public class Empty extends Piece {
    public static final Piece INSTANCE = new Empty();

    private Empty() {
        super(Color.NONE);
    }


    @Override
    protected boolean isInstanceReachable(final Vector vector, final Piece targetPiece) {
        throw new UnsupportedOperationException("비어 있는 칸입니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
