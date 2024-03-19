package chess.domain;

import java.util.Objects;

public class Point {

    private final File file;
    private final Rank rank;

    public Point(String file, int rank) {
        this.file = new File(file);
        this.rank = new Rank(rank);
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
