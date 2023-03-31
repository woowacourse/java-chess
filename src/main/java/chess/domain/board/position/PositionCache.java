package chess.domain.board.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionCache {

    private static final Map<String, Boolean> CACHE = new HashMap<>();

    static {
        final List<String> columns = Column.findPossibleColumnCandidates();
        final List<Integer> rows = Row.findPossibleRowCandidates();

        columns.stream()
               .flatMap(column -> rows.stream()
                                      .map(row -> column + row))
               .forEach(key -> CACHE.put(key, true));
    }

    public static boolean isNotCaching(final String positionCandidate) {
        return !CACHE.containsKey(positionCandidate);
    }
}
