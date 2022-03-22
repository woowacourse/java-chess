package chess;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, String> value;

    public Board(final Map<Position, String> value) {
        this.value = Map.copyOf(value);
    }

    public static Board create() {
        return new Board(new HashMap<>());
    }
}
