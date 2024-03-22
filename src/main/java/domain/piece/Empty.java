package domain.piece;

import domain.MovePath;
import domain.Position;
import domain.Side;

public class Empty extends Piece {

    public Empty() {
        super(Side.EMPTY);
    }

    @Override
    public boolean isRuleBroken(Position current, Position target, MovePath movePath) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
