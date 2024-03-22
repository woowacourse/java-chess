package domain.piece;

import domain.Position;
import domain.Side;
import java.util.Map;

public class Empty extends Piece {

    public Empty() {
        super(Side.EMPTY);
    }

    @Override
    public boolean isRuleBroken(Position current, Position target, Map<Position, Piece> pieces) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
