package domain.piece;

import domain.MovePath;
import domain.Position;
import domain.Side;

public class King extends Piece {

    public King(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return (source.isSameFile(target) || source.hasOneFileGap(target)) &&
                (source.isSameRank(target) || source.hasOneRankGap(target)) &&
                !source.equals(target);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
