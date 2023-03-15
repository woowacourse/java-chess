package techcourse.fp.chess.domain;

import java.util.List;

public final class Empty extends Piece {

    public Empty() {
        super(Color.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position sourcePosition, final Position targetPosition,
                                   final Color targetColor) {
        throw new UnsupportedOperationException();
    }
}
