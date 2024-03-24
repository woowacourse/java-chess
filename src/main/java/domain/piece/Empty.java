package domain.piece;

import domain.route.MovePath;
import domain.position.Position;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty();

    public Empty() {
        super(Side.EMPTY);
    }

    public static Empty instance() {
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
