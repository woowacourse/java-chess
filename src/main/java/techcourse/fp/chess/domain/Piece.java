package techcourse.fp.chess.domain;

import java.util.List;

public abstract class Piece {

    private final Side side;
    private Position position;

    protected Piece(Side side, Position position) {
        this.side = side;
        this.position = position;
    }

    public abstract List<Position> findMovablePositions();

    public abstract void move(Position targetPosition);
}
