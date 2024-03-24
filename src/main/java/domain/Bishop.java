package domain;

import domain.piece.Piece;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position current, Position target, MovePath movePath) {
        return false;
    }

//    @Override
//    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
//        checkBlockingPiece(target, pieces);
//        return current.isDiagonal(target);
//    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
