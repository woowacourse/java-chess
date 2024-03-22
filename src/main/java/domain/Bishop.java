package domain;

import domain.piece.Piece;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
        checkBlockingPiece(target, pieces);
        return current.isDiagonal(target);
    }
}
