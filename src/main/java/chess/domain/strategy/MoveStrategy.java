package chess.domain.strategy;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Deque;
import java.util.Map;

public interface MoveStrategy {
    Map<Direction, Deque<Position>> generateMovablePositions(Position position);
}
