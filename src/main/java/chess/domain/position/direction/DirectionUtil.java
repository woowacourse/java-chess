package chess.domain.position.direction;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DirectionUtil {
    public static List<Position> getPositionsSameDirectionDiagonalBetween(Position from, Position to) {
        int xAxisDelta = from.getXAxis().getValue() - to.getXAxis().getValue();
        int yAxisDelta = from.getYAxis().getValue() - to.getYAxis().getValue();
        int time = Math.abs(xAxisDelta);

        int xDirection = -(xAxisDelta / time);
        int yDirection = -(yAxisDelta / time);

        return IntStream.range(1, time)
                .mapToObj(idx -> getPositionWith(from, xDirection, yDirection, idx))
                .collect(Collectors.toList());
    }

    public static Position getPositionWith(Position from, int xDir, int yDir, int idx) {
        XAxis xAxis1 = XAxis.getByValue(from.getXAxis().getValue() + xDir * idx);
        YAxis yAxis1 = YAxis.getByValue(from.getYAxis().getValue() + yDir * idx);

        return Position.from(xAxis1, yAxis1);
    }

    public static List<Position> getPositionsSameYAxisBetween(Position from, Position to) {
        return YAxis.getBetween(from.getYAxis(), to.getYAxis()).stream()
                .map(yAxis -> Position.from(from.getXAxis(), yAxis))
                .collect(Collectors.toList());
    }

    public static List<Position> getPositionsSameXAxisBetween(Position from, Position to) {
        return XAxis.getBetween(from.getXAxis(), to.getXAxis()).stream()
                .map(xAxis -> Position.from(xAxis, from.getYAxis()))
                .collect(Collectors.toList());
    }

    public static boolean isInVerticalRange(Position from, Position other, int range) {
        return Math.abs(from.getYAxis().subtract(other.getYAxis())) <= range;
    }
}
