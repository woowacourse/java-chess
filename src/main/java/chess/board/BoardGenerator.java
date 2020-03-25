package chess.board;

import java.util.Map;

public interface BoardGenerator {
    Map<Coordinate, Tile> generate();
}
