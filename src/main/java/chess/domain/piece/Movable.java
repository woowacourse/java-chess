package chess.domain.piece;

import chess.domain.Position;

import java.util.List;

public interface Movable {
    List<Position> findPositions(final Position source, final Position target);
}
