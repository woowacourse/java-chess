package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessCoordinate {
    private final ChessXCoordinate x;
    private final ChessYCoordinate y;

    private static Map<String, ChessCoordinate> coordinateMap = new HashMap<>();

    static {
        for (ChessXCoordinate xValue : ChessXCoordinate.values()) {
            for (ChessYCoordinate yValue : ChessYCoordinate.values()) {
                coordinateMap.put(xValue.getSymbol() + yValue.getSymbol(), new ChessCoordinate(xValue, yValue));
            }
        }
    }

    private ChessCoordinate(ChessXCoordinate x, ChessYCoordinate y) {
        this.x = x;
        this.y = y;
    }

    public static ChessCoordinate valueOf(String symbol) {
        return Optional.ofNullable(coordinateMap.get(symbol))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌표입니다."));
    }

    public ChessXCoordinate getX() {
        return x;
    }

    public ChessYCoordinate getY() {
        return y;
    }
}
