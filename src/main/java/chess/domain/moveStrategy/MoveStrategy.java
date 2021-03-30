package chess.domain.moveStrategy;

import chess.domain.position.Position;

import java.util.List;

public interface MoveStrategy {
    List<List<Position>> movablePositions(Position position);
}
