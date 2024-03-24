package domain;

import domain.piece.Piece;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return source.isDiagonal(target);
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
