package chess.domain;

import java.util.List;

public interface Movable {
    List<Position> findMovablePositions(Position position);
}
