package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;
import java.util.Map;

public interface MoveStrategy {

    List<Position> findMovePath(Map<Position, GamePiece> board, Position source, Position target);

    List<Position> findKillPath(Position source, Position target);
}
