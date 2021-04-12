package domain.piece.objects;

import domain.piece.position.Position;
import domain.score.Score;

import java.util.Map;

public final class Empty extends Piece {
    private static final Empty CACHE = new Empty();

    public Empty() {
        super(".", Score.ZERO, true);
    }

    public static Piece create() {
        return CACHE;
    }

    @Override
    public boolean isSameColor(boolean value) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public boolean canMove2(Map<Position, Piece> board, Position start, Position end) {
        return false;
    }

    @Override
    public boolean existPath() {
        throw new RuntimeException("찾을 수 없음!");
    }

    @Override
    public void checkMovable(Position start, Position end, boolean turn) {
        throw new RuntimeException("빈 칸은 움직일 수 없습니다!");
    }
}
