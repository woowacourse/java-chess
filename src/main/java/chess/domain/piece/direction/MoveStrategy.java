package chess.domain.piece.direction;

import chess.domain.position.Position;

public interface MoveStrategy {
    Position move(final Position position);

    boolean isNorth();

    boolean isSouth();
}
