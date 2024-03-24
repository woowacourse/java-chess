package domain.piece;

import domain.route.MovePath;
import domain.position.Position;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return source.isSameFile(target) || source.isSameRank(target);
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
