package domain;

import domain.piece.Piece;
import java.util.Map;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    public boolean isQueen() {
        return true;
    }

    @Override
    protected boolean hasFollowedRule(Position current, Position target, MovePath movePath) {
        return false;
    }

//    @Override
//    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
//        checkBlockingPiece(target, pieces);
//        return current.isDiagonal(target) || current.isSameFile(target) || current.isSameRank(target);
//    }
}
