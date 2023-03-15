package domain.position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Positions {
    private static final String INVALID_POSITION = "존재하지 않는 위치입니다.";

    private static final Map<String, Position> positions = new HashMap<>();

    static {
        Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> new Position(file, rank)))
                .forEach(position -> positions.put(position.getName(), position));
    }

    private Positions() {
    }

    public static List<Position> of(String... positions) {
        return Arrays.stream(positions)
                .map(Positions::from)
                .collect(Collectors.toList());
    }

    public static Position from(String position) {
        if (positions.containsKey(position)) {
            return positions.get(position);
        }

        throw new IllegalArgumentException(INVALID_POSITION);
    }
}
