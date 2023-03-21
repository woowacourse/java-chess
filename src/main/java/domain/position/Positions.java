package domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Positions {
    private static final int STAY = 0;

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
        if (notEightDirections(source, destination)) {
            return Collections.emptyList();
        }

        return getBetweenPositions(source, destination);
    }

    private static boolean notEightDirections(final Position source, final Position destination) {
        return !source.isDiagonal(destination) && !source.isStraight(destination);
    }

    private static List<Position> getBetweenPositions(final Position source, final Position destination) {
        final int COUNT_BETWEEN = getMaxDifference(source, destination) - 1;
        final int oneStepRank = getOneStep(getRankDifference(source, destination));
        final int oneStepFile = getOneStep(getFileDifference(source, destination));

        return Stream.iterate(source.move(oneStepRank, oneStepFile),
                        (position) -> position.move(oneStepRank, oneStepFile))
                .limit(COUNT_BETWEEN)
                .collect(Collectors.toUnmodifiableList());
    }

    private static int getMaxDifference(final Position source, final Position destination) {
        final int rankDifference = getRankDifference(source, destination);
        final int fileDifference = getFileDifference(source, destination);

        return Math.max(Math.abs(rankDifference), Math.abs(fileDifference));
    }

    private static int getRankDifference(final Position source, final Position destination) {
        return destination.getRank() - source.getRank();
    }

    private static int getFileDifference(final Position source, final Position destination) {
        return destination.getFile() - source.getFile();
    }

    private static int getOneStep(final int difference) {
        return Integer.compare(difference, STAY);
    }
}
