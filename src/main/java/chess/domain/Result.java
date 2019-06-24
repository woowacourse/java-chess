package chess.domain;

import java.util.Map;

public class Result {
    private final double whiteResult;
    private final double blackResult;

    public Result(Map<Aliance,Double> result) {
        this.whiteResult = result.get(Aliance.WHITE);
        this.blackResult = result.get(Aliance.BLACK);
    }
}
