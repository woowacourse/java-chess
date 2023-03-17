package chessgame.domain.point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {
    private static final Map<String, Point> cache = new HashMap<>(64);

    private final File file;
    private final Rank rank;

    private Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
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

    public boolean isHorizontal(Point target) {
        return !isSameFileDistance(target, 0) && isSameRankDistance(target, 0);
    }

    public boolean isVertical(Point target) {
        return isSameFileDistance(target, 0) && !isSameRankDistance(target, 0);
    }

    public boolean isDiagonal(Point target) {
        if (isSameFileDistance(target, 0) || isSameRankDistance(target, 0)) {
            return false;
        }
        return Math.abs(fileDistance(target)) == Math.abs(rankDistance(target));
    }

    public boolean isInitialPoint(Rank initialRank) {
        return rank.distance(initialRank) == 0;
    }

    public boolean isSameFileDistance(Point target, int distance) {
        return Math.abs(fileDistance(target)) == distance;
    }

    public boolean isSameRankDistance(Point target, int distance) {
        return Math.abs(rankDistance(target)) == distance;
    }

    public int maxDistance(Point target) {
        int fileDifference = Math.abs(fileDistance(target));
        int rankDifference = Math.abs(rankDistance(target));

        return Math.max(fileDifference, rankDifference);
    }

    public int fileMove(Point target, int distance) {
        return fileDistance(target) / distance;
    }

    public int rankMove(Point target, int distance) {
        return rankDistance(target) / distance;
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
