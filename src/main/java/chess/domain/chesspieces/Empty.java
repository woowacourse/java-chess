package chess.domain.chesspieces;

import chess.domain.position.Position;

public class Empty extends Square {
    private static final Empty instance = new Empty();
    public final static String EMPTY_DISPLAY = ".";

    public Empty() {
        super(EMPTY_DISPLAY);
    }

    public static Empty getInstance() {
        return instance;
    }

    @Override
    public boolean movable(Position from, Position to) {
        throw new UnsupportedOperationException("empty는 이동할 수 없습니다.");
    }

    @Override
    public boolean isSamePlayer(Square target) {
        throw new UnsupportedOperationException("empty는 사용자를 비교할 수 없습니다.");
    }
}
