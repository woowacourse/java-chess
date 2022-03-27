package chess.domain.position;

import java.util.List;
import java.util.Objects;

public final class Direction {
    private final int dFile;
    private final int dRank;

    public Direction(int dFile, int dRank) {
        this.dFile = dFile;
        this.dRank = dRank;
    }

    Square add(File file, Rank rank) {
        return new Square(file.add(dFile), rank.add(dRank));
    }

    public Direction flipAboutX() {
        return new Direction(dFile, -1 * dRank);
    }

    public boolean hasSame(List<Direction> directions) {
        return directions.stream()
                .anyMatch(this::equals);
    }

    public boolean hasMultiple(List<Direction> directions) {
        return directions.stream()
                .anyMatch(this::canReach);
    }

    private boolean canReach(Direction direction) {
        return direction.equals(getUnitDirection());
    }

    public Direction getUnitDirection() {
        int x = this.dFile;
        int y = this.dRank;

        if (x == 0 || y == 0) {
            return getUnitDirectionWithZero(x, y);
        }

        return getUnitDirectionWithOutZero(x, y);
    }

    private Direction getUnitDirectionWithZero(int x, int y) {
        if (x == 0 && y == 0) {
            return new Direction(0, 0);
        }

        if (x == 0) {
            return new Direction(0, y / Math.abs(y));
        }

        return new Direction(x / Math.abs(x), 0);
    }

    private Direction getUnitDirectionWithOutZero(int x, int y) {
        int gcd = gcd(Math.abs(x), Math.abs(y));
        return new Direction(x / gcd, y / gcd);
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Direction direction = (Direction)o;
        return dFile == direction.dFile && dRank == direction.dRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dFile, dRank);
    }
}
