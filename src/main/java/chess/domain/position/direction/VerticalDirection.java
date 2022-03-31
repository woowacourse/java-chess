package chess.domain.position.direction;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import java.util.List;
import java.util.stream.Collectors;

public class VerticalDirection {

    public static List<Position> getPositionsSameXAxisBetween(Position from, Position to) {
        return XAxis.getBetween(from.getXAxis(), to.getXAxis()).stream()
                .map(xAxis -> Position.from(xAxis, from.getYAxis()))
                .collect(Collectors.toList());
    }

    public static boolean isInVerticalRange(Position from, Position other, int range) {
        return Math.abs(from.getYAxis().subtract(other.getYAxis())) <= range;
    }
}
