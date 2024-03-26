package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.route.Route;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty();

    public Empty() {
        super(Side.EMPTY);
    }

    public static Empty instance() {
        return INSTANCE;
    }

    @Override
    boolean hasFollowedRule(Position source, Position target, Route route) {
        return false;
    }
}
