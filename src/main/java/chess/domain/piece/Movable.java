package chess.domain.piece;

import chess.domain.Position;

import java.util.List;

public interface Movable {
    List<Position> findMoveAblePositions(final Position source, final Position target);
}
