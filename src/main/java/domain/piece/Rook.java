package domain.piece;

import domain.route.Route;
import domain.square.Square;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
