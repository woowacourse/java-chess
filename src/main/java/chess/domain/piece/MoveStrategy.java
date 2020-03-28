package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public interface MoveStrategy {

    List<Position> findMovePath(Position source, Position target);

    List<Position> findKillPath(Position source, Position target);
}
