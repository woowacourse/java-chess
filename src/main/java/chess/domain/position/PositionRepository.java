package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

public class PositionRepository {
    private static final List<Position> positions;

    private PositionRepository() {}

    static {
        positions = Arrays.stream(Rank.values())
                .flatMap(rank ->
                        Arrays.stream(File.values())
                                .map(file -> Position.of(file, rank))
                )
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public static List<Position> positions() {
        return positions;
    }
}
