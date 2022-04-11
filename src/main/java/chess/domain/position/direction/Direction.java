package chess.domain.position.direction;

import chess.domain.position.Position;

public interface Direction {
    boolean isOnDirection(Position from, Position to);
}
