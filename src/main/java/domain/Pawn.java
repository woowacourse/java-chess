package domain;

import domain.piece.Piece;
import java.util.Map;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isRuleBroken(Position current, Position target, MovePath movePath) {
        return false;
    }

//    @Override
//    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
//        checkBlockingPiece(target, pieces);
//
//        if (current.isSameRank(target) || isReverseMove(current, target)) {
//            return false;
//        }
//        if (current.isSameFile(target) && pieces.containsKey(target)) {
//            return false;
//        }
//        if (isInitPosition(current) && current.hasOnlyTwoRankGap(target)) {
//            return true;
//        }
//        if (current.hasOneDiagonalGap(target) && hasOpponentAtTarget(target, pieces)) {
//            return true;
//        }
//        return current.hasOneRankGap(target) && current.isSameFile(target);
//    }

    private boolean isReverseMove(Position current, Position target) {
        if (isBlack()) {
            return current.isRankIncreased(target);
        }
        return current.isRankDecreased(target);
    }

    private boolean isInitPosition(Position current) {
        if (isBlack()) {
            return current.isBlackPawnRank();
        }
        return current.isWhitePawnRank();
    }

    private boolean hasOpponentAtTarget(Position target, Map<Position, Piece> pieces) {
        return pieces.containsKey(target) && pieces.get(target).isOpponent(this);
    }
}
