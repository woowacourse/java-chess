package chess.domain.strategy;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.Deque;
import java.util.Map;

public class KingMoveStrategy implements MoveStrategy{

    @Override
    public Map<Direction, Deque<Position>> generateMovablePositions(Position position) {
        return null;
    }
}
