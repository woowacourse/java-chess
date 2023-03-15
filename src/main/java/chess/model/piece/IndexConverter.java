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
        final int offset = findOffsetByDirection(direction);

        return index + offset;
    }

    public static int findCount(final Direction direction, final int totalDistance) {
        final int offset = findOffsetByDirection(direction);

        if (offset == 0) {
            return offset;
        }
        return totalDistance / offset;
    }

    private static int findOffsetByDirection(final Direction direction) {
        return Arrays.stream(values())
                .filter(it -> it.direction.equals(direction))
                .findAny()
                .map(it -> it.offset)
                .orElse(0);
    }
}
