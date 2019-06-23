package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
        if (coordinateMap.containsKey(symbol)) {
            return coordinateMap.get(symbol);
        }
        throw  new IllegalArgumentException("존재하지 않은 좌표입니다.");
    }

    public static ChessCoordinate valueOf(ChessXCoordinate x, ChessYCoordinate y) {
        return valueOf(x.getSymbol() + y.getSymbol());
    }

    public static void forEachCoordinate(Consumer<ChessCoordinate> consumer) {
        List<ChessXCoordinate> xAxis = ChessXCoordinate.allAscendingCoordinates();
        List<ChessYCoordinate> yAxis = ChessYCoordinate.allAscendingCoordinates();
        yAxis.forEach(y -> xAxis.forEach(x -> consumer.accept(valueOf(x, y))));
    }

    public ChessXCoordinate getX() {
        return x;
    }

    public ChessYCoordinate getY() {
        return y;
    }

    @Override
    public String toString() {
        return x.getSymbol() + y.getSymbol();
    }
}
