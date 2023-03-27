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

    public Point left() {
        return new Point(file.left(), rank);
    }

    public Point right() {
        return new Point(file.right(), rank);
    }

    public Point leftUp() {
        return new Point(file.left(), rank.up());
    }

    public Point leftDown() {
        return new Point(file.left(), rank.down());
    }

    public Point rightUp() {
        return new Point(file.right(), rank.up());
    }

    public Point rightDown() {
        return new Point(file.right(), rank.down());
    }

    private Point leftUpL() {
        return new Point(file.left().left(), rank.up());
    }

    private Point leftDownL() {
        return new Point(file.left().left(), rank.down());
    }

    private Point upLeftL() {
        return new Point(file.left(), rank.up().up());
    }

    private Point upRightL() {
        return new Point(file.right(), rank.up().up());
    }

    private Point rightDownL() {
        return new Point(file.right().right(), rank.down());
    }

    private Point rightUpL() {
        return new Point(file.right().right(), rank.up());
    }

    private Point downLeftL() {
        return new Point(file.left(), rank.down().down());
    }

    private Point downRightL() {
        return new Point(file.right(), rank.down().down());
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

    public Point move(Direction direction) {
        if (direction == Direction.UP) return up();
        if (direction == Direction.DOWN) return down();
        if (direction == Direction.LEFT) return left();
        if (direction == Direction.RIGHT) return right();
        if (direction == Direction.LEFT_UP) return leftUp();
        if (direction == Direction.LEFT_DOWN) return leftDown();
        if (direction == Direction.RIGHT_UP) return rightUp();

        if (direction == Direction.LEFT_UP_L) return leftUpL();
        if (direction == Direction.LEFT_DOWN_L) return leftDownL();
        if (direction == Direction.UP_LEFT_L) return upLeftL();
        if (direction == Direction.UP_RIGHT_L) return upRightL();
        if (direction == Direction.RIGHT_DOWN_L) return rightDownL();
        if (direction == Direction.RIGHT_UP_L) return rightUpL();
        if (direction == Direction.DOWN_LEFT_L) return downLeftL();
        if (direction == Direction.DOWN_RIGHT_L) return downRightL();
        return rightDown();
    }
}
