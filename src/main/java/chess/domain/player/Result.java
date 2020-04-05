package chess.domain.player;

import java.util.HashMap;
import java.util.Map;

public enum Result {
    WIN("win"),
    LOSE("lose"),
    DRAW("draw");

    private static Map<Result, Result> opposite = new HashMap<>();

    static {
        opposite.put(WIN, LOSE);
        opposite.put(LOSE, WIN);
        opposite.put(DRAW, DRAW);
    }

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public Result getOpposite() {
        return opposite.get(this);
    }
}
