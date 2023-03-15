package domain.piece;

import domain.position.Position;

public interface Movable {
    boolean isMovable(Position source, Position destination);
}
