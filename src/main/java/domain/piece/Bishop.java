package domain.piece;

import domain.route.MovePath;
import domain.position.Position;

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
