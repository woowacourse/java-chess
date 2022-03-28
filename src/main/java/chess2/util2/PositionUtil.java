package chess2.util2;

import chess2.domain2.board2.Position;
import java.util.ArrayList;
import java.util.List;

public class PositionUtil {

    private PositionUtil() {
    }

    public static List<Position> positionsStraightBetween(Position from, Position to) {
        if (!from.isStraightPath(to)) {
            return List.of();
        }
        return positionsBetween(from, to);
    }

    private static List<Position> positionsBetween(Position from, Position to) {
        List<Position> positions = new ArrayList<>();
        Position next = from.oneStepToward(to);
        while (next != to) {
            positions.add(next);
            next = next.oneStepToward(to);
        }
        return positions;
    }
}
