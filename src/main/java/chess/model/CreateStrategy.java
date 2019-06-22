package chess.model;

import java.util.Map;

@FunctionalInterface
public interface CreateStrategy {
    Map<String, Tile> create();
}
