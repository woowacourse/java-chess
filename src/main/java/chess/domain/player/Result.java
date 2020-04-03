package chess.domain.player;

import java.util.HashMap;
import java.util.Map;

public enum Result {
    WIN,
    LOSE,
    DRAW;

    private static Map<Result, Result> opposite = new HashMap<>();

    static {
        opposite.put(WIN, LOSE);
        opposite.put(LOSE, WIN);
        opposite.put(DRAW, DRAW);
    }

    public Result getOpposite() {
        return opposite.get(this);
    }
}
