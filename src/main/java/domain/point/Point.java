package domain.point;

import java.util.Objects;

public class Point {
    private final File file;
    private final Rank rank;

    public Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point fromSymbol(String from) {
        File file = File.findBySymbol(String.valueOf(from.charAt(0)));
        Rank rank = Rank.findBySymbol(String.valueOf(from.charAt(1)));
        return new Point(file, rank);
    }

    public int findIndexFromLeft() {
        return file.getIndexFromLeft();
    }

    public int findIndexFromBottom() {
        return rank.getIndexFromBottom();
    }

    public Point up() {
        return new Point(file, rank.up());
    }

    public Point down() {
        return new Point(file, rank.down());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
