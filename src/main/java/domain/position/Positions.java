package domain.position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Positions {
    private static final Map<String, Position> positions = new HashMap<>();

    static {
        Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .forEach(position -> positions.put(position.getName(), position));
    }

    private Positions() {
    }

    public static List<Position> of(final String... positions) {
        return Arrays.stream(positions)
                .map(Positions::from)
                .collect(Collectors.toList());
    }

    public static Position from(final String position) {
        if (positions.containsKey(position.toUpperCase())) {
            return positions.get(position.toUpperCase());
        }

        return new Position(File.NOTHING, Rank.NOTHING);
    }

    public static List<Position> between(final Position source, final Position destination) {
        final int rankDifference = source.getRankDifference(destination);
        final int fileDifference = source.getFileDifference(destination);
        final Direction direction = Direction.of(rankDifference, fileDifference);

        if (Direction.NOTHING.equals(direction)) {
            return Collections.emptyList();
        }

        return getBetweenPositions(source, destination, direction);
    }

    private static List<Position> getBetweenPositions(final Position source,
                                                      final Position destination,
                                                      final Direction direction) {
        final int countBetweenPositions = getMaxDifference(source, destination) - 1;

        return Stream.iterate(source.move(direction), position -> position.move(direction))
                .limit(countBetweenPositions)
                .collect(Collectors.toUnmodifiableList());
    }

    private static int getMaxDifference(final Position source, final Position destination) {
        final int rankDifference = Math.abs(source.getRankDifference(destination));
        final int fileDifference = Math.abs(source.getFileDifference(destination));

        return Math.max(rankDifference, fileDifference);
    }
}
