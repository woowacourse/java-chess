package chess.model.piece;

import java.util.Arrays;

public enum IndexConverter {

    NORTH(Direction.NORTH, 8),
    EAST(Direction.EAST, 1),
    SOUTH(Direction.SOUTH, -8),
    WEST(Direction.WEST, -1),
    NORTH_EAST(Direction.NORTH_EAST, 9),
    NORTH_WEST(Direction.NORTH_WEST, 7),
    SOUTH_EAST(Direction.SOUTH_EAST, -7),
    SOUTH_WEST(Direction.SOUTH_WEST, -9);

    private final Direction direction;
    private final int offset;

    IndexConverter(final Direction direction, final int offset) {
        this.direction = direction;
        this.offset = offset;
    }

    public static int findNextIndex(final Direction direction, final int index) {
        final IndexConverter indexConverter = findConverterByDirection(direction);

        return indexConverter.offset + index;
    }

    public static int findCount(final Direction direction, final int totalDistance) {
        final IndexConverter indexConverter = findConverterByDirection(direction);

        return totalDistance / indexConverter.offset;
    }

    private static IndexConverter findConverterByDirection(final Direction direction) {
        return Arrays.stream(values())
                .filter(it -> it.direction.equals(direction))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("방향을 찾을 수 없습니다."));
    }
}
