package chess.domain;

import java.util.List;

public interface Movable {
    List<Path> findMovablePaths(Position position);
}
