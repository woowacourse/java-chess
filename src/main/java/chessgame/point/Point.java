package chessgame.point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {
    private static final Map<String, Point> cache = new HashMap<>(64);

    private final Rank rank;
    private final File file;

    private Point(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Point of(Rank rank, File file) {
        return cache.computeIfAbsent(toKey(rank, file), ignore -> new Point(rank, file));
    }

    private static String toKey(Rank rank, File file) {
        return rank.name() + file.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point)o;
        return rank == point.rank && file == point.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Point{" +
            "rank=" + rank +
            ", file=" + file +
            '}';
    }
}
