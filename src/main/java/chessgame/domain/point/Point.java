package chessgame.domain.point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Point {
    private static final Map<String, Point> cache = new HashMap<>(64);

    private final int pointNo;
    private final File file;
    private final Rank rank;

    private Point(int pointNo, File file, Rank rank) {
        this.pointNo = pointNo;
        this.file = file;
        this.rank = rank;
    }

    private Point(File file, Rank rank) {
        this.pointNo = 0;
        this.file = file;
        this.rank = rank;
    }

    public static Point of(int pointNo, File file, Rank rank) {
        return cache.computeIfAbsent(toKey(file, rank), ignore -> new Point(pointNo, file, rank));
    }

    public static Point of(File file, Rank rank) {
        return cache.computeIfAbsent(toKey(file, rank), ignore -> new Point(file, rank));
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public Point move(int fileMove, int rankMove) {
        return Point.of(file.move(fileMove), rank.move(rankMove));
    }

    public int fileDistance(Point point) {
        return this.file.distance(point.file);
    }

    public int rankDistance(Point point) {
        return this.rank.distance(point.rank);
    }

    public boolean isInitialPoint(Rank initialRank) {
        return rank.distance(initialRank) == 0;
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

    public List<Point> getSameFilePoints() {
        return rank.findSameFile()
            .stream()
            .map(rank -> Point.of(this.file, rank))
            .collect(Collectors.toList());
    }

    public String getFile() {
        return String.valueOf(file.getValue());
    }

    public String getRank() {
        return String.valueOf(rank.getValue());
    }
}
