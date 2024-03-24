package chess.domain.strategy;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import java.util.Queue;
import java.util.Map;

public interface MoveStrategy {
    Map<Direction, Queue<Position>> generateMovablePositions(Position position);
}
