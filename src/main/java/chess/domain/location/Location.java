package chess.domain.location;

import java.util.Objects;

public class Location {

    private static final int MIN_LOCATION = 1;
    private static final int MAX_LOCATION = 8;

    private final int x;
    private final int y;

    private Location(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Location of(final int x, final int y) {
        validateRange(x, y);
        return new Location(x, y);
    }

    private static void validateRange(final int x, final int y) {
        if (x < MIN_LOCATION || MAX_LOCATION < x) {
            throw new IllegalArgumentException("x값이 범위에서 벗어났습니다.");
        }
        if (y < MIN_LOCATION || MAX_LOCATION < y) {
            throw new IllegalArgumentException("y값이 범위에서 벗어났습니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isHorizontalOrVertical(final Location target) {
        return isHorizontal(target) || isVertical(target);
    }

    private boolean isHorizontal(final Location target) {
        return this.y == target.y;
    }

    private boolean isVertical(final Location target) {
        return this.x == target.x;
    }

    public boolean isDiagonal(final Location target) {
        return Math.abs(this.x - target.x) == Math.abs(this.y - target.y);
    }

    public boolean isAdjacent(final Location target) {
        return Math.abs(this.x - target.x) <= 1 && Math.abs(this.y - target.y) <= 1;
    }


}
