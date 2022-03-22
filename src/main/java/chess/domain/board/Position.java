package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> cache = new HashMap<>();

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

    public static Position from(final String positionValue) {
        validatePositionValue(positionValue);

        final String[] rankAndFile = positionValue.split(RANK_FILE_DELIMITER);
        final String rank = rankAndFile[RANK_INDEX];
        final String file = rankAndFile[FILE_INDEX];

        return cache.computeIfAbsent(positionValue, ignored -> cache.put(positionValue, new Position(rank, file)));
    }

    private static void validatePositionValue(final String positionValue) {
        if (positionValue.length() != 2) {
            throw new IllegalArgumentException("위치 정보가 유효하지 않습니다.");
        }
    }
}
