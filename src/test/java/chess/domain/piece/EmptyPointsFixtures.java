package chess.domain.piece;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.domain.board.EmptyPoints;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;

public class EmptyPointsFixtures {

    public static final EmptyPoints ALL = new EmptyPoints(createAll());

    private static List<Point> createAll() {
        return IntStream.range(LineNumber.MIN, LineNumber.MAX)
            .mapToObj(EmptyPointsFixtures::createLine)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private static List<Point> createLine(int verticalIndex) {
        return IntStream.rangeClosed(LineNumber.MIN, LineNumber.MAX)
            .mapToObj(horizontalIndex -> Point.of(verticalIndex, horizontalIndex))
            .collect(Collectors.toList());
    }

    public static EmptyPoints except(Point... points) {
        List<Point> all = createAll();
        for (Point point : points) {
            all.remove(point);
        }
        return new EmptyPoints(all);
    }
}
