package chess.domain.piece.attribute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;

public class Positions {
    private static final Map<String, Position> CACHED_POSITIONS = new HashMap<>();

    public static Set<Position> of(final String... positions) {
        return Arrays.stream(positions)
                .map(Position::from)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static void initializePositions() {
        for (final Rank rank : Rank.values()) {
            putPositions(rank);
        }
    }

    private static void putPositions(final Rank rank) {
        for (final File file : File.values()) {
            CACHED_POSITIONS.put(keyOf(file, rank), new Position(file, rank));
        }
    }

    private static String keyOf(final File file, final Rank rank) {
        return file.name() + rank.name();
    }

    protected static Position get(final File file, final Rank rank) {
        if (CACHED_POSITIONS.isEmpty()) {
            initializePositions();
        }
        return CACHED_POSITIONS.get(keyOf(file, rank));
    }
}
