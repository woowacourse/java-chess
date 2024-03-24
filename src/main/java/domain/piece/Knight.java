package domain.piece;

import domain.route.MovePath;
import domain.position.Position;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return source.hasTwoFileGap(target) && source.hasOneRankGap(target) ||
                source.hasOneFileGap(target) && source.hasTwoRankGap(target);
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
