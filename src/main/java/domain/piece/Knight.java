package domain.piece;

import domain.MovePath;
import domain.Position;
import domain.Side;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    public boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return source.hasTwoFileGap(target) && source.hasOneRankGap(target) ||
                source.hasOneFileGap(target) && source.hasTwoRankGap(target);
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
