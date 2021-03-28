package domain.piece.objects;

import domain.piece.position.Position;
import domain.score.Score;

import java.util.Map;

public final class Empty extends Piece {

    public Empty() {
        super(".", Score.ZERO, true);
    }

    public static Piece create() {
        return new Empty();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position start, Position end) {
        return false;
    }
}
