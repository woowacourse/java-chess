package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface MovingPattern {
    boolean isMovable(Map<Position, Piece> board, Position source, Position target);

    List<Position> findRoute(Position source, Position target);
}
