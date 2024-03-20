package chess.domain;

import java.util.Objects;

public class Point {

    private final File file;
    private final Rank rank;

    public Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Point(String file, int rank) {
        this(new File(file), new Rank(rank));
    }

    public boolean isDiagonal(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        return (double) fileDistance / rankDistance == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(file, point.file) && Objects.equals(rank, point.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
