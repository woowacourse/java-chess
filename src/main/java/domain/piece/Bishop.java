package domain.piece;

import domain.route.Route;
import domain.square.Square;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        return source.isDiagonal(target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}
