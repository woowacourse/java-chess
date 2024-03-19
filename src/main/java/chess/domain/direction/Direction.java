package chess.domain.direction;

import chess.domain.Position;
import java.util.List;

public interface Direction {
    List<Position> move(Position from);
}
