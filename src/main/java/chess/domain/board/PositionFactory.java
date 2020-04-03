package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

public class PositionFactory {
    private static final Map<String, Position> allPositions = createAllPositions();

    private static Map<String, Position> createAllPositions() {
        Map<String, Position> allPositions = new HashMap<>();

        for (Xpoint xPoint : Xpoint.values()) {
            for (Ypoint yPoint : Ypoint.values()) {
                allPositions.put(xPoint.getName() + yPoint.getName(), new Position(xPoint, yPoint));
            }
        }

        return allPositions;
    }

    public static Position of(String positionName) {
        if (!allPositions.containsKey(positionName)) {
            throw new IllegalArgumentException(positionName + "은(는) 존재하지 않는 위치입니다.");
        }
        return allPositions.get(positionName);
    }

    public static Position of(char xPoint, char yPoint) {
        String positionName = Xpoint.of(xPoint).getName() + Ypoint.of(yPoint).getName();
        return of(positionName);
    }

    public static Position of(int xPoint, int yPoint) {
        String positionName = Xpoint.of(xPoint).getName() + Ypoint.of(yPoint).getName();
        return of(positionName);
    }
}
