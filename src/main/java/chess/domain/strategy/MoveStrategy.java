package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.Queue;
import java.util.Map;

public interface MoveStrategy {
    Map<Direction, Queue<Position>> generateMovablePositions(Position position);
}
