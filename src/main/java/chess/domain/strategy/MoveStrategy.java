package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.List;
import java.util.Map;

public interface MoveStrategy {
    Map<Direction, List<Position>> generateMovablePositions(Position position);
}
