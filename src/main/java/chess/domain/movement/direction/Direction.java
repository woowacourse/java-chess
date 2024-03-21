package chess.domain.movement.direction;

import chess.domain.Position;
import java.util.List;

public interface Direction {

    boolean canReach(Position source, Position target, List<Position> obstacle);
}
