package domain.piece;

import domain.route.Route;
import domain.square.Square;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty();

    public Empty() {
        super(Side.EMPTY);
    }

    public static Empty instance() {
        return INSTANCE;
    }

    @Override
    public boolean hasFollowedRule(Square current, Square target, Route route) {
        return false;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.EMPTY;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
