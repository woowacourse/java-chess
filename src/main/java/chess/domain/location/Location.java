package chess.domain.location;

import java.util.Objects;
import java.util.stream.IntStream;

public class Location {

    private static final int MIN_LOCATION = 1;
    private static final int MAX_LOCATION = 8;
    private static final int LOCATION_SIZE = 2;
    private static final int CONVERSION_INDEX = 1;
    private static final Location[][] CACHE = new Location[MAX_LOCATION][MAX_LOCATION];

    static {
        IntStream.rangeClosed(MIN_LOCATION, MAX_LOCATION)
            .boxed()
            .flatMap(
                y -> IntStream.rangeClosed(MIN_LOCATION, MAX_LOCATION)
                    .mapToObj(x -> new Location(x, y))
            )
            .forEach(
                location ->
                    CACHE[location.y - CONVERSION_INDEX][location.x - CONVERSION_INDEX] = location
            );
    }

    private final int x;
    private final int y;

    private Location(final int x, final int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    private static Location bringCacheData(final int x, final int y) {
        try {
            return CACHE[y - CONVERSION_INDEX][x - CONVERSION_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new LocationCacheMissException(x, y);
        }
    }

    public static Location of(final int x, final int y) {
        return bringCacheData(x, y);
    }

    public static Location convert(final String location) {
        if (location.length() != LOCATION_SIZE) {
            throw new IllegalArgumentException("좌표가 잘못 입력되었습니다.");
        }
        int x = location.charAt(0) - 'a' + 1;
        int y = Character.getNumericValue(location.charAt(1));
        return Location.of(x, y);
    }

    private static void validateRange(final int x, final int y) {
        if (!isRange(x, y)) {
            throw new IllegalArgumentException("범위에서 벗어났습니다.");
        }
    }

    private static boolean isRange(final int x, final int y) {
        return (MIN_LOCATION <= x && x <= MAX_LOCATION)
            && (MIN_LOCATION <= y && y <= MAX_LOCATION);
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
        return Math.abs(subtractX(target)) <= 1 && Math.abs(subtractY(target)) <= 1;
    }

    public int subtractX(final Location target) {
        return target.x - this.x;
    }

    public int subtractY(final Location target) {
        return target.y - this.y;
    }

    public boolean isSameY(final int y) {
        return this.y == y;
    }

    public Location moveByStep(final int dx, final int dy) {
        return Location.of(x + dx, y + dy);
    }

    public boolean isRangeByStep(final int dx, final int dy) {
        return isRange(x + dx, y + dy);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
