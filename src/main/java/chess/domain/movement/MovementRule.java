package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public interface MovementRule {

    boolean isMovable(Position start, Position end, boolean isEnemyExistAtEnd);

    List<Position> findPath(Position start, Position end, boolean isEnemyExistAtEnd);
}
