package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.Deque;
import java.util.Map;

public interface MoveStrategy {
    Map<Direction, Deque<Position>> generateMovablePositions(Position position);
}
