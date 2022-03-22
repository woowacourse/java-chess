package chess.domain.board;

import static chess.domain.board.util.PositionUtil.fileToIdx;
import static chess.domain.board.util.PositionUtil.rankToIdx;

import java.util.HashMap;
import java.util.Map;

public class Position {

    private final int rankIdx;
    private final int fileIdx;

    private Position(String value) {
        char[] positionInfo = value.toCharArray();
        this.rankIdx = rankToIdx(positionInfo[0]);
        this.fileIdx = fileToIdx(positionInfo[1]);
    }

    public static Position of(String value) {
        return PositionCache.getCache(value);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rankIdx=" + rankIdx +
                ", fileIdx=" + fileIdx +
                '}';
    }

    private static class PositionCache {

        static Map<String, Position> cache = new HashMap<>(64);

        static Position getCache(String value) {
            return cache.computeIfAbsent(value, Position::new);
        }
    }
}
