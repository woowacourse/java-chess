package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<PositionKey, Position> cache = new HashMap<>();

    private static final int RANK_INDEX = 0;
    private static final int FILE_INDEX = 1;
    private static final String RANK_FILE_DELIMITER = "";

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private Position(final String file, final String rank) {
        this(File.from(file), Rank.from(rank));
    }

    public static Position of(final File file, final Rank rank) {
        final PositionKey positionKey = new PositionKey(file, rank);
        return cache.computeIfAbsent(positionKey, ignored -> new Position(file, rank));
    }

    public static Position from(final String positionValue) {
        validatePositionValue(positionValue);

        final String[] rankAndFile = positionValue.split(RANK_FILE_DELIMITER);
        final String rank = rankAndFile[RANK_INDEX];
        final String file = rankAndFile[FILE_INDEX];

        return of(File.from(file), Rank.from(rank));
    }

    private static void validatePositionValue(final String positionValue) {
        if (positionValue.length() != 2) {
            throw new IllegalArgumentException("위치 정보가 유효하지 않습니다.");
        }
    }

    static class PositionKey {
        private final File file;
        private final Rank rank;

        private PositionKey(final File file, final Rank rank) {
            this.file = file;
            this.rank = rank;
        }

        private PositionKey(final String file, final String rank) {
            this(File.from(file), Rank.from(rank));
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final PositionKey that = (PositionKey) o;
            return file == that.file && rank == that.rank;
        }

        @Override
        public int hashCode() {
            return Objects.hash(file, rank);
        }
    }
}
