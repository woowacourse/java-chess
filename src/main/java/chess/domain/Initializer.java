package chess.domain;

import java.util.Map;

public interface Initializer {
    Map<Position, Unit> create();
}
