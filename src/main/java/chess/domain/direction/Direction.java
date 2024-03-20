package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public interface Direction {

    boolean canReach(Position from, Position to, List<Position> obstacle);
}
