package domain.piece;

import domain.route.Route;
import domain.square.Square;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Square source, Square target, Route route) {
        return source.isSameFile(target) || source.isSameRank(target) || source.isDiagonal(target);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.QUEEN;
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
