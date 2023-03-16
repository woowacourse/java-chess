package techcourse.fp.chess.domain;

import java.util.List;

public final class Empty extends Piece {

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
