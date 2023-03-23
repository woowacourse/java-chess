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

    public boolean isHorizontal(Point target) {
        return file.distance(target.file) != 0 && rank.distance(target.rank) == 0;
    }

    public boolean isVertical(Point target) {
        return file.distance(target.file) == 0 && rank.distance(target.rank) != 0;
    }

    public boolean isVerticalFile(File file) {
        return this.file.distance(file) == 0;
    }

    public boolean isDiagonal(Point target) {
        if (file.distance(target.file) == 0 || rank.distance(target.rank) == 0) {
            return false;
        }
        return Math.abs(file.distance(target.file)) == Math.abs(rank.distance(target.rank));
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

    public int calculateDistance(Point point) {
        return Math.max(Math.abs(fileDistance(point)), Math.abs(rankDistance(point)));
    }

    public int eachFileMove(Point point) {
        return fileDistance(point) / calculateDistance(point);
    }

    public int eachRankMove(Point point) {
        return rankDistance(point) / calculateDistance(point);
    }

    public boolean canPawnAttack(Point source, Point target, int distance) {
        if (source.rankDistance(target) == distance && source.fileDistance(target) == distance) {
            return true;
        }
        return (source.rankDistance(target) == distance && source.fileDistance(target) == -distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
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
