package domain.piece;

import domain.MovePath;
import domain.Position;
import domain.Side;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty();

    public Empty() {
        super(Side.EMPTY);
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean hasFollowedRule(Position current, Position target, MovePath movePath) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
