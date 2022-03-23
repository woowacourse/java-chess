package domain;

import java.util.Map;

public interface BoardGenerator {

    public Map<Position, Piece> generate();
}
