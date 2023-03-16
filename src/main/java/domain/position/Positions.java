package domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Positions {
    private static final String INVALID_POSITION = "존재하지 않는 위치입니다.";
    private static final int FORWARD = 1;
    private static final int STAY = 0;
    private static final int BACKWARD = -1;

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

        int rankDifference = destination.getRank() - source.getRank();
        int fileDifference = destination.getFile() - source.getFile();
        final int count = getMaxDifference(rankDifference, fileDifference);

        return makeBetweenPositions(source, rankDifference, fileDifference, count);
    }

    private static boolean notEightDirections(final Position source, final Position destination) {
        return !source.isDiagonal(destination) && !source.isStraight(destination);
    }

    private static int getMaxDifference(final int rankDifference, final int fileDifference) {
        return Math.max(Math.abs(rankDifference), Math.abs(fileDifference));
    }

    private static int getStepOfMovement(final int difference) {
        if (difference > 0) {
            return FORWARD;
        }
        if (difference == 0) {
            return STAY;
        }

        return BACKWARD;
    }

    private static List<Position> makeBetweenPositions(final Position source, final int rankDifference,
                                                       final int fileDifference, final int count) {
        final List<Position> result = new ArrayList<>();
        Position next = source;
        for (int i = 0; i < count - 1; i++) {
            next = next.move(getStepOfMovement(rankDifference), getStepOfMovement(fileDifference));
            result.add(next);
        }

        return result;
    }
}
