package domain;

import java.util.Map;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target, Map<Position, Piece> pieces) {
        checkBlockingPiece(target, pieces);
        return (current.hasTwoFileGap(target) && current.hasOneRankGap(target)) ||
                (current.hasOneFileGap(target) && current.hasTwoRankGap(target));
    }
}
