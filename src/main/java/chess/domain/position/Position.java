package chess.domain.position;

import chess.domain.YAxis;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Position {
    private final XAxis xAxis;
    private final YAxis yAxis;

    private Position(XAxis xAxis, YAxis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public static Position from(XAxis xAxis, YAxis yAxis) {
        return Cache.cache.stream()
                .filter(position -> position.xAxis == xAxis && position.yAxis == yAxis)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("좌표가 존재하지 않습니다."));
    }

    @Override
    public String toString() {
        return "Position{" +
                "XAxis=" + xAxis +
                ", YAxis=" + yAxis +
                '}';
    }

    private static class Cache {

        private static final List<Position> cache;

        static {
            cache = Arrays.stream(XAxis.values()).
                    flatMap(xAxis -> Arrays.stream(YAxis.values())
                            .map(yAxis -> new Position(xAxis, yAxis)))
                    .collect(Collectors.toList());
        }
    }
}
