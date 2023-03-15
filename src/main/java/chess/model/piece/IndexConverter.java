package chess.model.piece;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum IndexConverter {

    NORTH(Direction.NORTH, index -> index + 8),
    EAST(Direction.EAST, index -> index + 1),
    SOUTH(Direction.SOUTH, index -> index - 8),
    WEST(Direction.WEST, index -> index - 1),
    NORTH_EAST(Direction.NORTH_EAST, index -> index + 9),
    NORTH_WEST(Direction.NORTH_WEST, index -> index + 7),
    SOUTH_EAST(Direction.SOUTH_EAST, index -> index - 7),
    SOUTH_WEST(Direction.SOUTH_WEST, index -> index - 9);

    private static final Map<Direction, Function<Integer, Integer>> CACHE = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(indexConverter -> indexConverter.direction,
                    indexConverter -> indexConverter.converter));

    private final Direction direction;
    private final Function<Integer, Integer> converter;

    IndexConverter(final Direction direction, final Function<Integer, Integer> converter) {
        this.direction = direction;
        this.converter = converter;
    }

    public static int findNextIndex(final Direction direction, final int index) {
        final Function<Integer, Integer> converter = CACHE.get(direction);

        return converter.apply(index);
    }
}
