package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        int rankDifference = destination.getRank() - source.getRank();
        int fileDifference = destination.getFile() - source.getFile();
        final int count = getMaxDifference(rankDifference, fileDifference);

//        final List<Position> result = new ArrayList<>();
//        Position next = source;
//        for (int i = 0; i < count - 1; i++) {
//            next = next.move(getOneStep(rankDifference), getOneStep(fileDifference));
//            result.add(next);
//        }

        return IntStream.range(1, count)
                .mapToObj(distance -> source.move(distance*getOneStep(rankDifference), distance*getOneStep(fileDifference)))
                .collect(Collectors.toList());
    }

    private static int getMaxDifference(final int rankDifference, final int fileDifference) {
        return Math.max(Math.abs(rankDifference), Math.abs(fileDifference));
    }

    private static int getOneStep(final int difference) {
        return Integer.compare(difference, STAY);
    }
}
