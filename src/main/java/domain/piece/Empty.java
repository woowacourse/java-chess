package domain.piece;

import domain.Score;

import java.util.Map;

public final class Empty extends Piece{

    public Empty() {
        super(".", Score.ZERO, true);
    }

    public static Piece create() {
        return new Empty();
    }

    @Override
    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        return false;
    }
}
