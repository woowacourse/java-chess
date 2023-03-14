package chessgame.domain.point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {
    private static final Map<String, Point> cache = new HashMap<>(64);

    private final File file;
    private final Rank rank;

    private Point(chessgame.domain.point.File file, chessgame.domain.point.Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point of(chessgame.domain.point.File file, chessgame.domain.point.Rank rank) {
        return cache.computeIfAbsent(toKey(file, rank), ignore -> new Point(file, rank));
    }

    private static String toKey(chessgame.domain.point.File file, chessgame.domain.point.Rank rank) {
        return file.name() + rank.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point)o;
        return file == point.file && rank == point.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Point{" +
            "rank=" + file +
            ", file=" + rank +
            '}';
    }
}
