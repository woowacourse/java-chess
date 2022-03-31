package chess.domain.position.direction;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalDirection {

    public static boolean isOnDiagonal(Position from, Position other) {
        int xAxisDelta = Math.abs(other.getXAxis().getValue() - from.getXAxis().getValue());
        int yAxisDelta = Math.abs(other.getYAxis().getValue() - from.getYAxis().getValue());

        return xAxisDelta == yAxisDelta;
    }

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

    private static Position getPositionWith(Position from, int xDir, int yDir, int idx) {
        XAxis xAxis1 = XAxis.getByValue(from.getXAxis().getValue() + xDir * idx);
        YAxis yAxis1 = YAxis.getByValue(from.getYAxis().getValue() + yDir * idx);

        return Position.from(xAxis1, yAxis1);
    }
}
