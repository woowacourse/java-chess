package chess.domain;

import java.util.List;

public abstract class Piece {

    abstract boolean isMovable(Position source, Position target);
    abstract List<Position> findPath(Position source, Position target);
}
