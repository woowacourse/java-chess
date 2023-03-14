package techcourse.fp.chess.domain;

import java.util.List;

public abstract class Piece {

    private final Side side;

    protected Piece(final Side side) {
        this.side = side;
    }

    public abstract List<Position> findMovablePositions(final Position basePosition);
}
