package chess.domain.position.direction;

import chess.domain.position.Position;
import chess.domain.position.YAxis;
import java.util.List;
import java.util.stream.Collectors;

public class HorizontalDirection {

    public static List<Position> getPositionsSameYAxisBetween(Position from, Position to) {
        return YAxis.getBetween(from.getYAxis(), to.getYAxis()).stream()
                .map(yAxis -> Position.from(from.getXAxis(), yAxis))
                .collect(Collectors.toList());
    }
}
